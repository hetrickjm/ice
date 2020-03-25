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

public class SeqTask extends Task {

	public SeqTask() {
		super();
		System.out.println("SeqTask() constructor");
		
	}

	/**
	 * This is another constructor for the SeqTask class.  It task a taskID and an action.
	 * @param id - id for the task
	 * @param action - an action to for the task
	 */
	public SeqTask(String id, Action action) {
		super(id, action);
		System.out.println("SeqTask(String id, Action action) constructor");
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
		// TODO - implement SeqTask.execute
		throw new UnsupportedOperationException();
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
		// TODO - implement SeqTask.execute
		throw new UnsupportedOperationException();
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
		// TODO - implement SeqTask.execute
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
		// TODO - implement SeqTask.execute
		throw new UnsupportedOperationException();
	}

}