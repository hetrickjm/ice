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
	 * The runWFID, or group workflow ID, is a unique value to identify the workflow
	 * 
	 * NOTE: Should probably DEPRECATE this and only use the parent class wfID attribute
	 */
	private String seqWorkflowID;

	/**
	 * This is the constructor for the RunWF class
	 */
	public SeqWF() {
		super();
		System.out.println("RunWF() constructor");
		
		this.setSeqWorkflowID("Run-ID");
		this.setCurrentStep(null);
		
	}   // end RunWF() constructor

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is another constructor for the RunWF class.  It takes a DataSet and a
	 * WorkflowDescription to bind together in the Workflow
	 * @param id - the id of the workflow
	 * @param set - the DataSet to bind with the WorkflowDescription in the Workflow
	 * @param description - the DataSet to bind with the WorkflowDescription in the Workflow
	 */
	public SeqWF(String id, DataSet set, WorkflowDescription description) {
		super(id, set, description);
		System.out.println("RunWF(DataSet set, WorkflowDescription description) constructor");
		
		this.setSeqWorkflowID(id);
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the seqWorkflowID attribute
	 * @return - the seqWorkflowID attribute
	 */
	public String getSeqWorkflowID() {
		return super.getWorkflowID();
	}   // end RunWF.getRunID()

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the seqWorkflowID attribute
	 * @param id - the ID to use in setting the seqWorkflowID attribute
	 */
	public void setSeqWorkflowID(String id) {
		super.setWorkflowID(id);
		//this.setWfID(seqWorkflowID);
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
		System.out.println("RunWF.nextMsg()");
		System.out.println("   msgIn: " + msgIn.toString());
		
		// Initi local vars
		Message msgOut = null,
				emptyMsg;
		int i = -1;
		boolean found = false;
		Task task = null;
		List <TaskStatus> taskStatusTable = super.getTaskStatusTable();  //easier to work with locally
		
		// What is the current status of the workflow?
		switch (super.getWorkflowStatus()) {
			case NOT_STARTED: 
			case IN_PROGRESS: 
				// Find the first Task that is not complete and process it
				for (i = 0; ((i < taskStatusTable.size()) && !found); i++) {
					if ( !(taskStatusTable.get(i).isComplete())) {
						found = true;
						task = taskStatusTable.get(i).getTask();
					}
				}
				
				// If there are not more tasks to execute then the workflow is complete
				if (found) {
					// Invoke the task to execute
					msgOut = (Message) task.execute(taskStatusTable.get(i - 1), msgIn);
					super.setWorkflowStatus(WorkflowState.IN_PROGRESS);
					
					emptyMsg = new Message();
					emptyMsg.setDataSetRef(msgIn.getDataSetRef());
					
					if (i < taskStatusTable.size()) {
						while (msgOut == null && taskStatusTable.get(i - 1).isComplete()) {
							// Note i is already incremented
							task = taskStatusTable.get(i).getTask();
							msgOut = (Message) task.execute(taskStatusTable.get(i), emptyMsg);
						}
					}
					
					// check if there are any more Tasks to execute AND
					// if the task just executed is complete
					// If so then the workflow is complete
					if (i >= taskStatusTable.size() && taskStatusTable.get(i - 1).isComplete()){
						super.setWorkflowStatus(WorkflowState.COMPLETE);
						
					}
				}
				else {
					// If a task is not found then the assumption is that all tasks are complete.
					super.setWorkflowStatus(WorkflowState.COMPLETE);
				}
				
				break;
				
			case COMPLETE: 
				break;
			
			case ERROR: 
				break;
			
			default:
		
		}
		
		if (msgOut == null)
			System.out.println("    msgOut: null");
		else
			System.out.println("   msgOut: " + msgOut.toString());
		
		return msgOut;
		
	}

}   // end class RunWF