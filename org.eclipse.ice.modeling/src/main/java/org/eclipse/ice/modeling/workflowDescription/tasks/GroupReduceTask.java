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

import java.util.*;
import org.eclipse.ice.modeling.workflow.*;
import org.eclipse.ice.modeling.workflowDescription.Criteria;
import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.states.WorkflowState;
import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.workflowDescription.tasks.*;

/**
 * The GroupReduceTask class si the task responsible for processing (reducing) a single sequence.
 * This is accomplished through the action which invokes the GroupWF's childWorkflow which is
 * the SeqWF
 * 
 * @author John Hetrick
 */
public class GroupReduceTask extends GroupTask {

	/**
	 * This is the constructor for the GroupReduce class
	 */
	public GroupReduceTask() {
		super();
		System.out.println("GroupReduceTask() constructor");
	}

	/**
	 * This is another constructor for the GroupReduce class that takes an Action
	 * @param id - the id of be used to set the taskID to
	 * @param act - the Action to add to the actionSet
	 */
	public GroupReduceTask(String id, Action act) {
		super(id, act);
		System.out.println("GroupReduceTask(String id, Action act) constructor");
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupReduceTask execute the action(s) associated
	 * with the Task.
	 */
	public Object execute() {
		System.out.println("GroupReduce.execute()");
		
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupReduceTask execute the action(s) associated
	 * with the Task.  This method implements how to invoke child workflows associated
	 * with sequences that need to be reduced
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(Object obj) {
		System.out.println("GroupReduce.execute(Object obj)");
		
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupReduceTask execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * TaskStatus so the Task can us it to persist state
	 * @param wf
	 * @param obj - generic parameter to be used in executing the task. For this class the obj should be a Message.
	 * @return Object -
	 */
	public Object execute(Workflow wf, Object obj) {
		System.out.println("GroupReduce.execute(Workflow workflow, Object obj)");
		
		int i      = -1,
			seqNum = -1;
		String teskID = "";
		boolean found = false;
		Message msgIn = (Message) obj,
				msgOut = null;
		Criteria criterion = null;
		GroupWF wfg = (GroupWF) wf;
		List <TaskStatus> taskStatusTable = wf.getTaskStatusTable();
		List <Workflow> childWorkflowSet = wfg.getChildWorkflowSet();
		TaskStatus taskStatus = null;
		
		// Get the Sequence number from the MetaData.  The sequence
		// number is the index for the childWorkflow (SeqWF) from the set of childWorkflows
		seqNum = msgIn.getDataSetRef().getMetaData().getSequenceNumber();
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
					msgOut = (Message) super.getAction(i).execute(childWorkflowSet.get(seqNum), msgIn);
				}
				
				// if the childworkflow is complete then this group task is complete
				// Set the taskStatus completion criteria &
				// Set the taskStatus State to complete
				if (childWorkflowSet.get(seqNum).isComplete()) {
					criterion = new Criteria();
					criterion.setCriteria("CHILD.COMPLETE");
					taskStatus.addCriteria(criterion);
					taskStatus.setState(WorkflowState.COMPLETE);
				}
				
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
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the Task execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * Workflow so the Task can us it to persist state
	 * @param taskStatus - the TaskStatus to set as needed
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(TaskStatus taskStatus, Object obj) {
		System.out.println("GroupReduce.execute(TaskStatus taskStatus, Object obj)");
		
		return null;
		
	}

}