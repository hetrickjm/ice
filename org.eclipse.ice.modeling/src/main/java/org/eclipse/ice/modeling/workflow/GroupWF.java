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

import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflowDescription.*;
import org.eclipse.ice.modeling.workflowDescription.tasks.Task;
import org.eclipse.ice.modeling.states.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * The GroupWF specializes Workflow.  GroupWF is a workflow to process WorkflowDescriptions
 * designed to process groups of Data Sets (Sequences) Workflows.
 * 
 * @author John Hetrick
 */
public class GroupWF extends Workflow {
	
	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(GroupWF.class);
	
	/**
	 * The childWFSet attribute holds the set of child workflows that the
	 * GroupWF can contain.  These child workflows are anticipated to be RunWF.
	 * 
	 * NOTE: Currently this attribute us being initialized to 2.  In the future
	 * this should be a dynamic list who's nodes are created on when needed.
	 */
	private List<Workflow> childWorkflowSet;
	
	/**
	 * This is the constructor for the GroupWF class.
	 */
	public GroupWF() {
		super();
		logger.debug("GroupWF() constructor");
		
		// Intialize the list of child workflows
		this.childWorkflowSet = new ArrayList <Workflow>();
		
	}   // end RunSetWF() constructor

	/**
	 * This is another constructor for the GroupWF class. It takes a DataSet and WorkflowDescription
	 * parameters to bind together in the Workflow.
	 * @param id - the ID of the workflow
	 * @param set - the DataSe to bind with the WorkflowDescription in the Workflow
	 * @param description - the WorkflowDescription to bind with the DataSet in the Workflow
	 */
	public GroupWF(String id, DataSet set, IWorkflowDescription description) {
		super(id, set, description);
		logger.debug("GroupWF(String id, DataSet set, WorkflowDescription description) constructor");
		
		// Intialize the list of child workflows
		this.childWorkflowSet = new ArrayList <Workflow>();

	}
	
	/**
	 * This is a getter method to return the childWorkflowss attribute.
	 * @return List <Workflow>
	 */
	public List<Workflow> getChildWorkflowSet() {
		logger.debug("GroupWF.getChildWorkflowSet()");
		return this.childWorkflowSet;
	}

	/**
	 * This is a setter method to add a child workflow to the childWfs attribute.
	 * 
	 * @param - the Workflow to add to the set of childWFs
	 * 
	 * @return void
	 */
	public void addChildWorkflow(Workflow workflow) {
		logger.debug("GroupWF.gaddChildWorkflow(Workflow workflow)");
		this.childWorkflowSet.add(workflow);
		
	}   // end RunSetWF.setChildWFs(Workflow childWFs)

	/**
	 * This is a getter method to return a single childWorkflows found in
	 * the childWorkflowSet.
	 * 
	 * @param key - String that is the key to find a specific child workflow.  The key consists of: key = meta.getInstrumentID() +
	 * "/" + meta.getExperimentID() +
	 * "/" + meta.getGroupID() +
	 * "/WFS-" + meta.getSequenceNumber();
	 * @return Workflow
	 */
	public Workflow findChildWorkflow(String key) {
		logger.debug("GroupWF.findChildWorkflow(String key)");
		
		boolean done = false;
		Workflow wf = null;
		
		for (int i = 0;(i < this.childWorkflowSet.size() && !done); i++) {
			if (key == this.childWorkflowSet.get(i).getWorkflowID()) {
				wf = this.childWorkflowSet.get(i);
				done = true;
			}
		}
		
		return wf;
	}

	/**
	 * This method is invoked by the workflow engine to have the workflow process an
	 * incoming message or action
	 * 
	 * This is the method from overides the same method of the parent class, Workflow.
	 * 
	 * @param msgIn - msgIn is the incoming message to be recognized and then to take
	 * action on
	 * 
	 * @return Message - represents the action
	 */
	public Message handleMsg(Message msgIn) {
		logger.debug("GroupWF.handleMsg(Message msgIn)\n\tmsgIn: ", msgIn.toString());
		
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

}   // end class GroupWF