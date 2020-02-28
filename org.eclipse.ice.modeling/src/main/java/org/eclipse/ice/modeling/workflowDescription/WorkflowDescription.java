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

import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.workflow.*;

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
public class WorkflowDescription {

	/**
	 * The taskList attribute is the set of Tasks that make up the WorkflowDescription
	 */
	private Task[] taskList;
	
	/**
	 * CURRENTLY THIS IS FOR EXPLORATORY PURPOSES AND MAY BE CHANGED
	 * OR DEPRECATED
	 * 
	 * The completionCriteria attribute holds the criteria for evaluating if the completion of the WorkflowDescription.  
	 * 
	 * NOTE: This should really be a List where steps can be added dynamically
	 */
	private CompletionCriteria completionCriteria;
	
	/**
	 * THIS IS FOR EXPLORATORY PURPOSES AND MAY BE DEPRECATED
	 * 
	 * The curIndex attribute is the index of the current Task being executed
	 * 
	 * Possibly this needs to be removed since the WorkflowDescription class should not hold workflow processing state.
	 */
	private int curIndex;
	
	/**
	 * THIS IS FOR EXPLORATORY PURPOSES AND MAY BE DEPRECATED
	 * 
	 * The numTask attribute is the number of Tasks in the WorkflowDescription
	 */
	private int numTasks;
	/**
	 * This attribute is the unique identifier for the workflow description
	 */
	private String workflowDescriptionID;

	/**
	 * This is the constructor for the WorkflowDescription class.
	 */
	public WorkflowDescription() {
		System.out.println("Procedure() constructor");
		
		// Create a default set of steps
		// This should really be a List where steps can be added dynamically
		numTasks = 3;
		taskList = new Task[numTasks];
		System.out.println( "##### BEGIN:  CREATE SETPS FOR PROCEDURE " );
		for (int i=0; i < numTasks; i++) {
			taskList[i] = new Task();
			//System.out.println("   step " +i + " " + taskList[i].getSuccess().getSuccessMsg());
		}
		System.out.println( "##### END:  CREATE SETPS FOR PROCEDURE " );
		
		// Seet the step index to 0 the first element.
		curIndex = 0;
		completionCriteria = new CompletionCriteria("Procedure Completion Criteria");
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * Notionally this method returns the next Task in the WorkflowDescription.
	 * Need to figure out where the evaluation of the current Task is and is it
	 * complete before going to the next Task.  Currently this simply returns the next Task
	 * regardless of the state of the current Task.
	 * @param currentTask - curTask is the Task that is currently in progress or the last Task to have been completed
	 */
	public Task nextTask(Task currentTask) {
		System.out.println("Procedure.nextStep()");
		
		// Find the current step in the list of steps and
		// then return the next step
		if (currentTask == null) {
			curIndex = 0;
			return taskList[curIndex];
		}
		else {
			curIndex++;
			return taskList[curIndex];
		}
	}

	/**
	 * This is a setter method to add Tasks to the WorkflowDescription.
	 * @param task - the task to be added to the taskList attribute
	 */
	public void addTask(Task task) {
		System.out.println("Procedure.addStep(Step step)");
		
		// take in a single step and append it to the list of current steps
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method returns the success criteria of the current Task.  This method is likely
	 * to be deprecated as the entity calling this method should already have a reference
	 * to the Task.
	 * @param currentTask - a reference to the current Task of the WorkflowDescription that is being executed
	 */
	public SuccessCriteria getStepSuccess(Task currentTask) {
		System.out.println("Procedure.getStepSuccess(Step currentStep)");
		
		return currentTask.getSuccess();   
	}

	/**
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
	 * This is a setter method to set the completionCriteria attribute of the WorkflowDescription
	 * @param criteria - the completion criteria for the WorkflowDescription
	 */
	public void setCompleteCriteria(CompletionCriteria criteria) {
		// TODO - implement WorkflowDescription.setCompleteCriteria
		throw new UnsupportedOperationException();
	}


}   // end class Procedure