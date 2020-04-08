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


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.states.WorkflowState;
import org.eclipse.ice.modeling.workflowDescription.*;
import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.workflowDescription.tasks.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * The SeqWF class is the workflow class for Sequences (runs) to be processed.  It specializes Workflow
 * 
 * @author John Hetrick
 */
public class SeqWF extends Workflow {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(SeqWF.class);
	
	/**
	 * This is the constructor for the RunWF class
	 */
	public SeqWF() {
		super();
		logger.debug("SeqWF() constructor");
		
	}   // end RunWF() constructor

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is another constructor for the RunWF class.  It takes a DataSet and a
	 * WorkflowDescription to bind together in the Workflow
	 * @param id - the id of the workflow
	 * @param set - the DataSet to bind with the WorkflowDescription in the Workflow
	 * @param description - the WorkflowDescription to bind with the DataSet in the Workflow
	 */
	public SeqWF(String id, DataSet set, IWorkflowDescription description) {
		super(id, set, description);
		logger.debug("SeqWF(DataSet set, WorkflowDescription description) constructor");
		
	}   // end RunWF.setRunID(String id)

	/**
	 * This method is invoked by the workflow engine to have the workflow process an
	 * incoming message or action.
	 * 
	 * Currently this is not an intelligent workflow.  The SeqWF workflow processes Tasks one
	 * at a time in order they are in the WorkflowDescription.  The tasks have been added
	 * to the taskStatusTable in that order.  Each Task is executed to completion before the
	 * next Task is invoked.  Curretnly the recieved message is not being paired to a
	 * specific Task.
	 * 
	 * This is the method from overides the same method of the parent class, Workflow.
	 * 
	 * @param msgIn - msgIn is the incoming message to be recognized and then to take
	 * action on
	 */
	public Message handleMsg(Message msgIn) {
		logger.debug("SeqWF.handleMsg(Message msgIn)\n\tmsgIn: ", msgIn.toString());
		
		// Init local vars
		Message msgOut = null,
				msg    = msgIn;
		int i = -1;
		boolean found    = false,
				stopLoop = false;
		Task task = null;
		List <TaskStatus> taskStatusTable = null;  //easier to work with locally
		WorkflowStatus workflowStatus = null;
		
		// Get the Sequence number for the DataSet from the message is related to.
		// The sequence number is the index to the set of child workflows and to
		// the correct WorkflowStatus from the workflowStatusTable
		int seqNum = msgIn.getDataSetRef().getMetaData().getSequenceNumber();
		workflowStatus = super.getWorkflowStatusTable().get(seqNum);
		taskStatusTable = workflowStatus.getTaskStatusTable();
		
		// What is the current status of the workflow?
		switch (workflowStatus.getWorkflowState()) {
			case NOT_STARTED: 
				workflowStatus.setWorkflowState(WorkflowState.IN_PROGRESS);
				super.setWorkflowStatus(WorkflowState.IN_PROGRESS);
					
			case IN_PROGRESS: 
				// Loop through the Group Tasks until workflow is complete or
				// There is a message to send out
				while (!workflowStatus.isComplete() && (msgOut == null) && !stopLoop) {
					// Find the first Task that is not complete and invoke it
					for (i = 0; ((i < taskStatusTable.size()) && !found); i++) {
						if ( !(taskStatusTable.get(i).isComplete())) {
							found = true;
							task = taskStatusTable.get(i).getTask();
							//msgOut = (Message) task.execute(taskStatusTable.get(i), msgIn);
							msgOut = (Message) task.execute(taskStatusTable.get(i), msg);
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
					
					// if all tasks complete mark workflow as complete
					// Note this assumes sequential execution of tasks.  All previous
					// tasks assumed complete.  Really just checking if the last
					// task is complete
					// i is index of last task + 1 (from for loop above)
					if (i >= taskStatusTable.size() && taskStatusTable.get(i - 1).isComplete()) {
						workflowStatus.setWorkflowState(WorkflowState.COMPLETE);
						super.setWorkflowStatus(WorkflowState.COMPLETE);
					}
					
				}   // end while loop
				
				break;
				
			case COMPLETE: 
				break;
			
			case ERROR: 
				break;
			
			default:
		
		}
		
		if (msgOut == null)
			logger.debug("\tmsgOut: ");
		else
			logger.debug("\tmsgOut: ", msgIn.toString());
		
		return msgOut;
		
	}

}   // end class RunWF