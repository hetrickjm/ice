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

import org.eclipse.ice.modeling.states.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * WorkflowStatus is an entity that is responsible for holding the status of a workflow.  The status of a workflow
 * has holds the current state of the workflow and the state of the Tasks (TaskStatus) that belong to the workflow.
 * It is anticipated that the Workflow will contain a set of these WorkflowStatuses to accommodate workflows that
 * maintain a single status or those that need to maintain multiple statuses such as in the case of a Group workflow
 * 
 * @author John Hetrick
 */
public class WorkflowStatus {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(WorkflowStatus.class);
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The workflowState holds the state of a workflow.
	 */
	private WorkflowState workflowState;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The status attribute holds the current state of the workflow
	 */
	private List<TaskStatus> taskStatusTable = null;

	/**
	 * This is the constructor for the WorkflowStatus class
	 */
	public WorkflowStatus() {
		logger.debug("WorkflowRepo() constructor");
		// initialize the attributes
		this.workflowState = WorkflowState.NOT_STARTED;
		this.taskStatusTable = new ArrayList <TaskStatus>();
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the workflowState attribute
	 */
	public WorkflowState getWorkflowState() {
		return this.workflowState;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to returning the workflowState attribute
	 * @param state - the WorkflowState value to use in setting the workflowState attribute
	 */
	public void setWorkflowState(WorkflowState state) {
		this.workflowState = state;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the taskStatusTable attribute.
	 */
	public List<TaskStatus> getTaskStatusTable() {
		return this.taskStatusTable;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to return the taskStatusTable attribute.
	 * @param taskStatuses - a set of TaskStatuses to use in setting the TaskStatusTable attribute
	 */
	public void setTaskStatusTable(List <TaskStatus> taskStatuses) {
		this.taskStatusTable = taskStatuses;
	}

	/**
	 * This method adds a TaskStatus to the askStatusTable attribute
	 * @param status - the TaskStatus to add to the taskStatusTable
	 */
	public void addTaskStatus(TaskStatus status) {
		System.out.println("WorkflowStatus.addTaskStatus(TaskStatus status)");
		
		this.taskStatusTable.add(status);
	}

	/**
	 * This method evaluates if the WorkflowStatus is complete or not.  It returns true if
	 * it is complete or false if it is not complete.
	 */
	public boolean isComplete() {
		logger.debug("WorkflowStatus.isComplete()");
		
		if (this.workflowState == WorkflowState.COMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

}