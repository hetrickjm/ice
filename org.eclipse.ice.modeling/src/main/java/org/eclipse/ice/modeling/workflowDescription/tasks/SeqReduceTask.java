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
package org.eclipse.ice.modeling.workflowDescription.tasks;

import org.eclipse.ice.modeling.states.WorkflowState;
import org.eclipse.ice.modeling.workflow.*;
import org.eclipse.ice.modeling.workflowEngine.Message;
import org.eclipse.ice.modeling.workflowDescription.Criteria;
import org.eclipse.ice.modeling.workflowDescription.action.Action;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * This SeqReduceTask class is a single step in a WorkflowDescription focused on
 * reducing a DataSet.
 * 
 * As with other Tasks this specialized Task holds no state.
 * 
 * @author John Hetrick
 */
public class SeqReduceTask extends SeqTask {

	/**
	 * This is the constructor for the SeqReduceTask class
	 */
	public SeqReduceTask() {
		super();
		System.out.println("SeqReduceTask() constructor");
	}

	/**
	 * This is another constructor for the SeqReduceTask class
	 * @param id - the ID for the Task
	 * @param action - an action for the task
	 */
	public SeqReduceTask(String id, Action action) {
		super(id, action);
		System.out.println("SeqReduceTask(String id, Action action) constructor");
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the Task execute the action(s) associated
	 * with the Task.
	 * For the AR Workflow system this is returning the message to be
	 * sent to a Reducer system.
	 */
	public Object execute() {
		System.out.println("SeqReduceTask.execute()");
		
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the Task execute the action(s) associated
	 * with the Task.
	 * For the AR Workflow system this is returning the message to be
	 * sent to a Reducer system.
	 * 
	 * NOTE: consider renaming to "execute()"
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(Object obj) {
		System.out.println("SeqReduceTask.execute(Object obj)");
		
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the Task execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * Workflow so the Task can us it to persist state
	 * @param wf
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(Workflow wf, Object obj) {
		System.out.println("SeqReduceTask.execute(Workflow wf, Object obj)");
		
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the Task execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * Workflow so the Task can us it to persist state
	 * 
	 * @param taskStatus - the TaskStatus to set as needed
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(TaskStatus taskStatus, Object obj) {
		System.out.println("SeqReduceTask.execute(TaskStatus taskStatus, Object obj)");
		int i = -1;
		// Recast parameters
		Message msgIn = (Message) obj,
				msgOut = null;
		Criteria criterion = null;
		
		// Check the status of the task
		switch (taskStatus.getState()) {
			case NOT_STARTED:
				// if not started generate the outgoing message
				// All action to be executed.  Note there should only be 1 for this class
				for (i = 0; (i < super.numberOfActions()); i++) {
					msgOut = (Message) super.getAction(i).execute();
					msgOut.setDataSetRef(msgIn.getDataSetRef());
				}
				
				// Set the state of the TaskStatus
				taskStatus.setState(WorkflowState.IN_PROGRESS);
				break;
			case IN_PROGRESS:
				// If not then record the incoming message type if any.
				// Not sure what to do if the Task is IN_PROGRESS and is invoked with
				//     not incoming message
				criterion = new Criteria();
				criterion.setCriteria(msgIn.getMsgType());
				taskStatus.addCriteria(criterion);
				
				// Checkif the Task is now complete. This means checking the
				// CompletionCriteria
				if (taskStatus.isComplete())
					taskStatus.setState(WorkflowState.COMPLETE);
				
				break;
				
			case COMPLETE:
				// Should an Error be thrown here?
				break;
			
			case ERROR:
				// Not clear yet under what conditions cause an error or what to do
				break;
			
			default:
		
		}
		
		return msgOut;
	}

}