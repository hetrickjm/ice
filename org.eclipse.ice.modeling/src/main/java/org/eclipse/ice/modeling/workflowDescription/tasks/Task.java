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
import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.workflow.*;
import org.eclipse.ice.modeling.workflowDescription.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * This Task class is a single step in a WorkflowDescription.  While it
 * may be a single step (or activity) in a workflow description does
 * not mean it is atomic.  It could be a single task that has several
 * actions that need to be performed to complete the task.
 * 
 * The current thinking is that a Task holds no state.  The workflow
 * that it is associated with will hold the state.  This is so the
 * WorkflowDescription and Tasks can be defined a-priory, then
 * associated with a Data Set at some point in time.  This would
 * allow for Tasks to be part of multiple different WorkflowDescriptions
 * which would be dynamically associated with Data Sets when necessary.
 * 
 * @author John Hetrick
 */
public class Task {

	/**
	 * The action attribute of type Action is a set of 1 or more actions 
	 * that are necessary to complete the the Task.
	 */
	private List <Action> actionSet;
	
	/**
	 * The success attribute of type SuccessCriteria hold the criteria to 
	 * evaluate if a Task has successfully been completed.  
	 * NOTE: THIS IS CURRENTLY A CONCEPT BUT THE DETAILS HAVE NOT BEEN 
	 * WORKED OUT YET.
	 */
	private CompletionCriteria completionCriteria;
	
	/**
	 * The taskID attribute is a unique identifier for the Task.  There are
	 * two types of tasks for the AR Workflow system.  One for Groups and
	 * one for Sequences (runs).
	 */
	private String taskID;

	/**
	 * This is the constructor for the Task class
	 */
	public Task() {
		System.out.println("Task() constructor");
		
		// set an inital default action.
		this.actionSet = new ArrayList <Action>(); 
		
		//Init the completion criteria
		this.completionCriteria = null;
		
	}   // end Task() constructor;

	/**
	 * This is another constructor for the Task class that takes and Action.
	 * @param id - the id of be used to set the taskID to
	 * @param act - the Action to add to the actionSet
	 */
	public Task(String id, Action act) {
		System.out.println("Task(String id, Action act) constructor");
		
		this.taskID = id;
		this.actionSet = new ArrayList <Action>();
		this.actionSet.add(act);
		
		// Init the completion criteria
		this.completionCriteria = null;
		
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is a getter to return the taskId attribute for the Task
	 */
	public String getTaskID() {
		return this.taskID;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is a setter to set the taskId attribute for the Task
	 * 
	 * NOTE: This should be set on Task create so I am not sure this method is
	 * really needed.
	 * @param id - id to use to set the taskID attribute
	 */
	public void setTaskID(String id) {
		this.taskID = id;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the action attribute.
	 * 
	 * NOTE: This currently returns the action indicated by the index
	 * value passed in
	 * @param index
	 * @return the msg
	 */
	public Action getAction(int index) {
		System.out.println("Task.getsetAction(int index");
		
		// Check if there are any actions and
		// if the index passed in is "in-bounds"
		if ((index < 0) || (index > (this.actionSet.size() - 1)))
			return null;
		
		return this.actionSet.get(index);
	}   // end Task.getAction

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to add an Action to the set of actions for the Task
	 * @param act - the action to add to the list of actions
	 */
	public void addAction(Action act) {
		System.out.println("Task.setActionAction act()");
		
		this.actionSet.add(act);
		
	}   // end Task.setAction

	/**
	 * This method returns the number of actions in the actionSet
	 */
	public int numberOfActions() {
		return this.actionSet.size();
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is a getter to return the completion (MsgsCriteria) 
	 * attribute for the Task
	 * @return - the completion citeria
	 */
	public CompletionCriteria getCompletionCriteria() {
		System.out.println("Task.getCompletionCriteria()");
		return this.completionCriteria;
	}   // end Task.getSuccess()

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is a setter to set the completionCriteria attribute
	 * @param criteria - criteria to be used to set the completion attribute
	 */
	public void setCompletionCriteria(CompletionCriteria criteria) {
		System.out.println("Task.setCompletionCriteria(MsgsCriteria criteria)");
		this.completionCriteria = criteria;
	}   
	
	/**
	 * This method checks if the Task is complete.  It takes in a status 
	 * and compares it to the completionCriteria.
	 * @param status - status to compare against the completion criteria
	 */
	public boolean isTaskComplete(List <Criteria> status) {
		System.out.println("Task.isTaskComplete(List <Criteria> status)");
		
		return this.completionCriteria.isComplete(status);
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
	 * @return Object - the result of executing the Action(s)
	 */
	public Object execute() {
		System.out.println("Task.execute()");
		
		// THIS IS A BAD IMPLEMENTATION BECAUSE IT ASSUMES ONE ACTION
		int i = -1;
		Object result = null;
		
		for (i = 0; i < this.numberOfActions(); i++) {
			result = this.actionSet.get(i).execute();
		}
		
		// Check if the returned object is a Message
		// Currently no other type of Object other than Message and null have
		// been implemented
		if (result != null) {
			if (result instanceof Message)
				return (Message) result;
			else
				return result;
		}
		
		return result;
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
		System.out.println("Task.execute(Object obj)");
		// THIS IS A BAD IMPLEMENTATION BECAUSE IT ASSUMES ONE ACTION
		int i = -1;
		Object result = null;
		
		for (i = 0; i < this.numberOfActions(); i++) {
			result = this.actionSet.get(i).execute(obj);
		}
		
		// Check if the returned object is a Message
		// Currently no other type of Object other than Message and null have
		// been implemented
		if (result != null) {
			if (result instanceof Message)
				return (Message) result;
			else
				return result;
		}
		
		return result;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the Task execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * Workflow so the Task can us it to persist state
	 * @param workflow - the workflow that provides context and is used to capture
	 * and maintain state
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(Workflow wf, Object obj) {
		// TODO - implement Task.execute
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
		// TODO - implement Task.execute
		throw new UnsupportedOperationException();
	}

}   // end class Task