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
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupTask execute the action(s) associated
	 * with the Task.
	 */
	public Object execute() {
		System.out.println("GroupTask.execute()");
		
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupTask execute the action(s) associated
	 * with the Task.
	 * For the AR Workflow system this is returning the message to be
	 * sent to a Reducer system.
	 * 
	 * NOTE: consider renaming to "execute()"
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
	 */
	public Object execute(Workflow wf, Object obj) {
		// TODO - implement GroupTask.execute
		throw new UnsupportedOperationException();
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
		// TODO - implement GroupTask.execute
		throw new UnsupportedOperationException();
	}

}