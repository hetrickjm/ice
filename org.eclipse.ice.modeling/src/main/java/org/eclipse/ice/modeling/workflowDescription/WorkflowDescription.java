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
package org.eclipse.ice.modeling.workflowDescription;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.workflow.*;
import org.eclipse.ice.modeling.workflowDescription.tasks.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * The WorkflowDescription holds the set of steps and the instructions for executing those step
 * for the processing of a Data Set.
 * 
 * My current thinking is that this class does not hold state, only Tasks
 * and Completion Criteria so the Workflow, which does hold state, can determine
 * when the WorkflowDescription has completed
 * 
 * The current thinking is that a WorkflowDescription holds no state.  The workflow that it is associated with the WorkflowDescription will hold the state.  This is so the WorkflowDescription and Tasks can be defined a-priory, then associated with a Data Set at some point in time.  This would allow for WorkflowDescriptions used across multiple different Workflows which would be dynamically associated with Data Sets when necessary.
 * 
 * @author John Hetrick
 */
public class WorkflowDescription implements IWorkflowDescription {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(WorkflowDescription.class);
	
	/**
	 * The taskList attribute is the set of Tasks that make up the WorkflowDescription
	 */
	private List<Task> taskList;
	
	/**
	 * CURRENTLY THIS IS FOR EXPLORATORY PURPOSES AND MAY BE CHANGED
	 * OR DEPRECATED
	 * 
	 * The completionCriteria attribute holds the criteria for evaluating if the completion of the WorkflowDescription.  
	 * 
	 * NOTE: This should really be a List where steps can be added dynamically
	 */
	private CompletionCriteria completionCriteria = null;
	
	/**
	 * This attribute is the unique identifier for the workflow description
	 */
	private String workflowDescriptionID;
	
	/**
	 * This attribute is an enumeration that specifies the type or workflow description this is.  It is
	 * either for a Group or a Sequence (run)
	 */
	private WorkflowDescriptionType workflowDescriptionType;

	/**
	 * This is the constructor for the WorkflowDescription class.
	 */
	public WorkflowDescription() {
		logger.debug("WorkflowDescription() constructor");
		
		// Init the workflow description type and ID
		this.workflowDescriptionID = "--";
		this.workflowDescriptionType = WorkflowDescriptionType.NONE;
	
		this.taskList = new ArrayList <Task>();
	}

	/**
	 * This is another constructor for the WorkflowDescription class.  It a value for the
	 * workflowDescriptionID and workflowDescriptionType
	 * 
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * @param id - a String to use as the workflow description ID and set the
	 * workflowDescriptionID attribute
	 * @param type - type of workflow description to initialize the workflowDescriptionType attribute
	 */
	public WorkflowDescription(String id, WorkflowDescriptionType type) {
		logger.debug("WorkflowDescription(String id, WorkflowDescriptionType type) constructor");
		
		// Init the workflow description type and ID
		this.workflowDescriptionID = id;
		this.workflowDescriptionType = type;
		this.taskList = new ArrayList <Task>();
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * Notionally this method returns the a Task in the WorkflowDescription
	 * that is specified by the index parameter.  Need to figure out where
	 * the evaluation of the current Task is and is it complete before going
	 * to the next Task.  Currently this simply returns the next Task
	 * regardless of the state of the current Task.
	 * @param index - the index for the Task to be retrieved
	 * 
	 * @return Task - the Task at the index
	 */
	public Task getTask(int index) {
		logger.debug("WorkflowDescription.getTask(int index)");
		
		// If the index is greater than the number of tasks,
		// the index is out of bounds so return null.
		if (index > (this.taskList.size() -1)) {
			return null;
		}
		else {
			return this.taskList.get(index);
		}
	}

	/**
	 * This is a setter method to add Tasks to the WorkflowDescription.
	 * 
	 * @param task - the task to be added to the taskList attribute
	 */
	public void addTask(Task task) {
		logger.debug("WorkflowDescription.addStep(Task task)");
		
		// As long as the in coming task is valid add it to the taskList
		if (task != null) {
			System.out.println("\tTask != null");
			this.taskList.add(task);
		}
		
		// else do nothing.  Not the currect solution. Need to throw exception
		System.out.println("\tTask added successfully");
		
	}

	/**
	 * DEPRECATE: CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method returns the completion criteria of the current Task.  This method is likely
	 * to be deprecated as the entity calling this method should already have a reference
	 * to the Task.
	 * 
	 * @param currentTask - a reference to the current Task of the WorkflowDescription 
	 * that is being executed
	 */
	public CompletionCriteria getTaskCompletionCriteria(Task currentTask) {
		return currentTask.getCompletionCriteria();   
	}

	/**
	 * DEPRECATE!
	 * 
	 * This is a getter method that returns the WorkflowDescription's completionCriteria
	 */
	public CompletionCriteria getCompleteCriteria() {
		return this.completionCriteria;
	}

	/**
	 * This is a setter method to set the completionCriteria attribute of the WorkflowDescription
	 * @param criteria - the completion criteria for the WorkflowDescription
	 */
	public void setCompleteCriteria(CompletionCriteria criteria) {
		this.completionCriteria = criteria;
	}

	/**
	 * This is a getter method that returns the workflowDescriptionID attribute
	 */
	public String getWorkflowDescriptionID() {
		return this.workflowDescriptionID;
	}

	/**
	 * This is a setter method to set the workflowDescriptionID attribute
	 * @param id  - id to use to set the workflowDescriptionID attribute
	 */
	public void setWorkflowDescriptionID(String id) {
		this.workflowDescriptionID = id;
	}

	/**
	 * This is a getter method that returns the workflowDescriptionType attribute
	 * @return - type of WorkflowDescription this is for
	 */
	public WorkflowDescriptionType getWorkflowDescriptionType() {
		return this.workflowDescriptionType;
	}

	/**
	 * This is a setter method that sets the workflowDescriptionType attribute
	 * @param type - WorkflowDescriptionType to use in setting the workflowDescriptionType
	 * attribute
	 */
	public void setWorkflowDescriptionType(WorkflowDescriptionType type) {
		this.workflowDescriptionType = type;
	}

	/**
	 * This is a getter method to return the number of tasks in the taskList attribute
	 * 
	 * @return int - the number of tasks in the Workflow
	 */
	public int getNumberOfTasks() {
		return taskList.size();
	}

}   // end class WorkflowDescription