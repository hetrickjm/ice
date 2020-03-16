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
import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.workflow.*;

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
	 * The actionSet attribute of type Action is a set of 1 or more actions 
	 * that are necessary to complete the the Task.
	 */
	private int currentAction = -1;
	
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
		this.currentAction = -1;                 // -1 means no actions in set
		
		//Init the completion criteria
		this.completionCriteria = null;
		
	}   // end Task() constructor;

	/**
	 * This is another constructor for the Task class that takes and Action.
	 * 
	 * @param id - the id of be used to set the taskID to
	 * @param act - the Action to add to the actionSet
	 */
	public Task(String id, Action act) {
		System.out.println("Task(String id, Action act) constructor");
		
		this.taskID = id;
		this.actionSet = new ArrayList <Action>();
		this.currentAction = -1;
		this.actionSet.add(act);
		this.currentAction = 0;    // set to the first action... just added
		
		// Init the completion criteria
		this.completionCriteria = null;
		
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the action attribute.
	 * 
	 * NOTE: This currently returns the action indicated by the index 
	 * value passed in
	 * 
	 * @return the msg
	 */
	public Action getAction(int index) {
		System.out.println("Task.getsetAction(int index");
		
		// Check if there are any actions and
		// if the index passed in is "in-bounds"
		if ((currentAction == -1) || (index > (this.actionSet.size() -1)))
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
		//If the action set is null then create it
		this.actionSet.add(act);
		//this.currentAction++;
		
	}   // end Task.setAction

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
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the Task execute the action(s) associated 
	 * with the Task.
	 * For the AR Workflow system this is returning the message to be 
	 * sent to a Reducer system.
	 * 
	 * NOTE: consider renaming to "execute()"
	 */
	public Message execute() {
		System.out.println("Task.execute()");
		
		// Execute the current action
		// Check if the returned object is a Message
		Object obj = this.actionSet.get(this.currentAction).execute();
		
		if (obj != null) {
			if (obj instanceof Message)
				return (Message) obj;
		}
		
		return null;
		
	}

	/**
	 * This method checks if the Task is complete.  It takes in a status 
	 * and compares it to the completionCriteria.
	 * @param status - status to compare against the completion criteria
	 */
	public boolean isTaskComplete(Object status) {
		System.out.println("Task.isTaskComplete(Object status)");
		
		return false;
	}

}   // end class Task