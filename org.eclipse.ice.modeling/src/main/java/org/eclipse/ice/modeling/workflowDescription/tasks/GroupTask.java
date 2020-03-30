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

import org.eclipse.ice.modeling.workflow.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * The GroupTask class specializes Task.  It is used to specify group tasks.  Need
 * to figure out if there is a generic behvior of all group tasks that can be
 * added in here.  Otherwise this may need to be deprecated.
 * 
 * Note: This may be an abstract class to be specialized by a specific group task
 * 
 * @author John Hetrick
 */
public class GroupTask extends Task {

	/**
	 * This is the constructor for the GroupTask class
	 */
	public GroupTask() {
		super();
		System.out.println("GroupTask() constructor");
	}

	/**
	 * This is another the constructor for the GroupTask class that takes an Action
	 * @param id - the id of be used to set the taskID to
	 * @param act - the Action to add to the actionSet
	 */
	public GroupTask(String id, Action act) {
		super(id, act);
		System.out.println("GroupTask(String id, Action act) constructor");
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupTask execute the action(s) associated
	 * with the Task.
	public Object execute() {
		System.out.println("GroupTask.execute()");
		
		return null;
	}
	 */

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupTask execute the action(s) associated
	 * with the Task.
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(Object obj) {
		System.out.println("GroupTask.execute(Object obj)");
		
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupTask execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * Workflow so the Task can us it to persist state
	 * @param workflow - the workflow that provides context and is used to capture
	 * and maintain state
	 * @param obj - generic parameter to be used in executing the task
	public Object execute(Workflow wf, Object obj) {
		System.out.println("GroupTask.execute(Workflow wf, Object obj)");
		
		return null;
	}
	 */

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupTask execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * TaskStatus so the Task can us it to persist state
	 * @param taskStatus - the TaskStatus to set as needed
	 * @param obj - generic parameter to be used in executing the task
	public Object execute(TaskStatus taskStatus, Object obj) {
		System.out.println("GroupTask.execute(TaskStatus taskStatus, Object obj)");
		
		return null;
	}
	 */

}