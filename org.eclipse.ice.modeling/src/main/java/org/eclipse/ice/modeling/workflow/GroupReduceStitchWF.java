/*******************************************************************************
 * Copyright (c) 2019- UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - John Hetrick
 *******************************************************************************/
package org.eclipse.ice.modeling.workflow;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.states.WorkflowState;
import org.eclipse.ice.modeling.workflowDescription.*;
import org.eclipse.ice.modeling.workflowDescription.tasks.Task;
import org.eclipse.ice.modeling.workflowEngine.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * The GroupReduceStitchWF specializes GroupWF which specializes Workflow.  GroupReduceStitchWF
 * is a workflow to manage the processing of WorkflowDescriptions designed for specifically
 * for a group that is to Reduce each sequence (as it comes in) and stitch it together
 * immediately with the other sequences that are part of the group.  The bulk of the processing is
 * handled in the parent classes.  This class is specializing only the part to manage sets of
 * TaskStatus tables, one for each sequence the group contains.
 * 
 * @author John Hetrick
 */
public class GroupReduceStitchWF extends GroupWF {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(GroupReduceStitchWF.class);
	
	/**
	 * This is the constructor for the GroupReduceStichWF
	 */
	public GroupReduceStitchWF() {
		super();
		logger.debug("GroupReduceStitchWF() constructor");
		
	}

	/**
	 * This is another constructor for the GroupReduceStitchWF class. It takes a DataSet and WorkflowDescription
	 * parameters to bind together in the Workflow.
	 * @param id - ID of the workflow
	 * @param set - the DataSet to bind with the WorkflowDescription in the Workflow
	 * @param description - the WorkflowDescription to bind with the DataSet in the Workflow
	 */
	public GroupReduceStitchWF(String id, DataSet set, WorkflowDescription description) {
		super(id, set, description);
		//System.out.println("GroupReduceStitchWF(String id, DataSet set, WorkflowDescription description) constructor");
		logger.debug("GroupReduceStitchWF(String id, DataSet set, WorkflowDescription description) constructor");
		
	}

	/**
	 * This method is invoked by the workflow engine to have the workflow process an
	 * incoming message or action
	 * 
	 * This is the method from overides the same method of the parent class, Workflow.
	 * @param msgIn - msgIn is the incoming message to be recognized and then to take action on
	 * @return Message - represents the action
	 */
	public Message handleMsg(Message msgIn) {
		//System.out.println("GroupReduceStitchWF.handleMsg(Message msgIn)");
		//System.out.println("   msgIn: " + msgIn.toString());
		logger.debug("GroupReduceStitchWF.handleMsg(Message msgIn)\n\t msgIn: {}", msgIn.toString());
		
		Message msgOut = null,
				msg    = msgIn;
		int i = -1;
		boolean found    = false,
				stopLoop = false;
		Task task = null;
		List <TaskStatus> taskStatusTable = null;  //easier to work with locally
		WorkflowStatus workflowStatus = null;
		
		// Get the Sequence from the Message DataSet.  The sequence
		// number is the index to the set of child workflows and to the set
		// of workflowStatusTable
		int seqNum = msgIn.getDataSetRef().getMetaData().getSequenceNumber();
		workflowStatus = super.getWorkflowStatusTable().get(seqNum);
		taskStatusTable = workflowStatus.getTaskStatusTable();
		
		// The switch is evaluating the entire group workflow status
		switch (super.getWorkflowStatus()) {
			case NOT_STARTED: 
				super.setWorkflowStatus(WorkflowState.IN_PROGRESS);
			case IN_PROGRESS: 
				// This switch is for the state for the specific sequences group tasks
				switch (workflowStatus.getWorkflowState()) {
					case NOT_STARTED: 
						workflowStatus.setWorkflowState(WorkflowState.IN_PROGRESS);
					case IN_PROGRESS: 
						// Loop through the Group Tasks for the specific sequence
						// until workflow is complete or There is a message to send out
						while (!workflowStatus.isComplete() && (msgOut == null) && !stopLoop) {
							// Find the first Task that is not complete and invoke it
							for (i = 0; ((i < taskStatusTable.size()) && !found); i++) {
								if ( !(taskStatusTable.get(i).isComplete())) {
									found = true;
									task = taskStatusTable.get(i).getTask();
									msgOut = (Message) task.execute(this, msg);
								}
							}
							
							// If task !complete stop the loop and wait for the next
							// incoming event (Message)
							if (!taskStatusTable.get(i - 1).isComplete())
								stopLoop = true;
							else {
								found = false;
								
								// Clear out the msg as there is no new msgIn.
								// Create a new Message so the msgIn is not over written
								if (msg == msgIn) {
									msg = new Message("");
									msg.setDataSetRef(msgIn.getDataSetRef());
								}
							}
							
							// if all tasks complete mark group seq status as complete
							// Note this assumes sequential execution of tasks.  All previous
							// tasks assumed complete.  Really just checking if the last
							// task is complete
							// i is index of last task + 1 (from for loop above)
							if (i >= taskStatusTable.size() && taskStatusTable.get(i - 1).isComplete()) {
								workflowStatus.setWorkflowState(WorkflowState.COMPLETE);
								
								// If all the other workflowStatuses are complete
								// Set the group workflow status to complete
								found = false;
								for (int j = 0; (j < super.getWorkflowStatusTable().size()) && !found; j++) {
									if (!super.getWorkflowStatusTable().get(j).isComplete()) {
										// an incomplete groupSeqWorkflow was found
										found = true;
									}
								}
								
								if (!found)
									super.setWorkflowStatus(WorkflowState.COMPLETE);
							}
							
						}   // end while loop
						
						break;
				
					case COMPLETE: 
						break;
					
					case ERROR: 
						break;
					
					default:
				}   // end inner switch
				
				break;
				
			case COMPLETE: 
				break;
			
			case ERROR: 
				break;
			
			default:
		
		}
		
		return msgOut;
	}

}   // end class GroupReduceStitchWF