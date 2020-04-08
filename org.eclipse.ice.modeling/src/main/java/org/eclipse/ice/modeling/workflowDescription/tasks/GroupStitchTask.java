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

import java.util.List;

import org.eclipse.ice.modeling.states.WorkflowState;
import org.eclipse.ice.modeling.workflow.*;
import org.eclipse.ice.modeling.workflowDescription.Criteria;
import org.eclipse.ice.modeling.workflowDescription.action.Action;
import org.eclipse.ice.modeling.workflowEngine.Message;

/**
 * The GroupStichTask class handles the stitching together of sequences that belong to a group
 * 
 * @author John Hetrick
 */
public class GroupStitchTask extends GroupTask {

	/**
	 * This is the constructor for the GroupStitchTask class
	 */
	public GroupStitchTask() {
		super();
		System.out.println("GroupStitchTask() constructor");
	}

	/**
	 * This is another constructor for the GroupStitchTask class that takes an ID and an Action
	 * @param id - the id of be used to set the taskID to
	 * @param act - the Action to add to the actionSet
	 */
	public GroupStitchTask(String id, Action act) {
		super(id, act);
		System.out.println("GroupStitchTask(String id, Action act) constructor");
	}

	/**
	 * DEPRECATE! If not implemented let the parent class handle this method
	 * 
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupStitchTask execute the action(s) associated
	 * with the Task.
	 */
	public Object execute() {
		System.out.println("GroupStitchTask(String id, Action act) constructor");
		return super.execute();
	}

	/**
	 * DEPRECATE! If not implemented let the parent class handle this method
	 * 
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupStitchTask execute the action(s) associated
	 * with the Task.
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(Object obj) {
		// TODO - implement GroupStich.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupStitchTask execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * Workflow so the Task can us it to persist state
	 * @param wf
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(Workflow wf, Object obj) {
		System.out.println("GroupStitchTask.execute(Workflow workflow, Object obj)");
		
		int i      = -1,
			seqNum = -1;
		String teskID = "";
		boolean found = false;
		Message msgIn = (Message) obj,
				msgOut = null;
		Criteria criterion = null;
		GroupWF wfg = (GroupWF) wf;
		List <TaskStatus> taskStatusTable = null;
		List <Workflow> childWorkflowSet = null;
		TaskStatus taskStatus = null;
		
		// Get the Sequence number from the MetaData.  The sequence
		// number is the index for the childWorkflow (SeqWF) from the set of childWorkflows
		seqNum = msgIn.getDataSetRef().getMetaData().getSequenceNumber();
		taskStatusTable = wfg.getWorkflowStatusTable().get(seqNum).getTaskStatusTable();
		childWorkflowSet = wfg.getChildWorkflowSet();
		String taskID = this.getTaskID();
		
		// Get the TaskStatus for the current Task from the Workflow TaskStatusTable
		for (i = 0; ((i < taskStatusTable.size()) && !found); i++) {
			if ( taskID == taskStatusTable.get(i).getTask().getTaskID()) {
				found = true;
				taskStatus = taskStatusTable.get(i);
			}
		}

		// Check the status of the task
		switch (taskStatus.getState()) {
			case NOT_STARTED:
				// Set the state of the TaskStatus
				taskStatus.setState(WorkflowState.IN_PROGRESS);
				
			case IN_PROGRESS:
				// if not started generate the outgoing message
				// All action to be executed.  Note there should only be 1 for this class
				for (i = 0; (i < super.numberOfActions()); i++) {
					msgOut = (Message) super.getAction(i).execute(null, msgIn);
				}
				
				// For stitching there is no child workflow just the group action of
				// stitching.  Once the stitching message is generated the group task
				// is complete.
				criterion = new Criteria();
				criterion.setCriteria("COMPLETE");
				taskStatus.addCriteria(criterion);
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

	/**
	 * DEPRECATE! If not implemented let the parent class handle this method
	 * 
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupStitchTask execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * TaskStatus so the Task can us it to persist state
	 * @param taskStatus - the TaskStatus to set as needed
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(TaskStatus taskStatus, Object obj) {
		// TODO - implement GroupStich.execute
		throw new UnsupportedOperationException();
	}
}