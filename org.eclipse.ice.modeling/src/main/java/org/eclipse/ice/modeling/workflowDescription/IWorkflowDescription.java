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

import org.eclipse.ice.modeling.workflowDescription.tasks.*;

public interface IWorkflowDescription {

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
	Task getTask(int index);

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
	CompletionCriteria getTaskCompletionCriteria(Task currentTask);

	/**
	 * This is a getter method to return the number of tasks in the taskList attribute
	 * 
	 * @return int - the number of tasks in the Workflow
	 */
	int getNumberOfTasks();

}