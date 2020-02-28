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
 * This Task class is a single step in a WorkflowDescription.  While it may be a single step
 * (or activity) in a workflow description does not mean it is atomic.  It could be a single task that
 * has several actions that need to be performed to complete the task.
 * 
 * The current thinking is that a Task holds no state.  The workflow that it is associated with will hold the state.  This is so the WorkflowDescription and Tasks can be defined a-priory, then associated with a Data Set at some point in time.  This would allow for Tasks to be part of multiple different WorkflowDescriptions which would be dynamically associated with Data Sets when necessary.
 * 
 * @author John Hetrick
 */
public class Task {

	/**
	 * The action attribute of type Action is a set of 1 or more actions that are necessary to complete the the Task.
	 */
	private Action[] action;
	
	/**
	 * The success attribute of type SuccessCriteria hold the criteria to evaluate if a Task has successfully been completed.  NOTE: THIS IS CURRENTLY A CONCEPT BUT THE DETAILS HAVE NOT BEEN WORKED OUT YET.
	 */
	private SuccessCriteria success;

	/**
	 * This is the constructor for the Task class
	 */
	public Task() {
		System.out.println("Step() constructor");
		
		// set an inital default action.
		action = new ActionMsg[1];
		success = new SuccessCriteria ( "Step Success Criteria" );
		
	}   // end Step() constructor;

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the action attribute.
	 * 
	 * NOTE: This currently just returns the first action if there are multiple.  Need to
	 * figure out if all actions should be returned or all a specific action to be returned
	 * @return the msg
	 */
	public Action getAction() {
		return this.action[0];
	}   // end Step.getAction

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to add an Action to the set of actions for the Task
	 * @param act - the action to add to the list of actions
	 */
	public void setAction(Action act) {
		this.action[0] = act;
	}   // end Step.setAction

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is a getter to return the success (successCriteria) attribute for the Task
	 * @return the success
	 */
	public SuccessCriteria getSuccess() {
		System.out.println("Step.getSuccess()");
		return success;
	}   // end Step.getSuccess()

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is a setter to set the success attribute (successCriteria)
	 * @param success - the successCriteria to set the success attribute
	 */
	public void setSuccess(SuccessCriteria success) {
		this.success = success;
	}   
	
	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the Task execute the action(s) associated with the Task.
	 * For the AR Workflow system this is returning the message to be sent to a Reducer system.
	 * 
	 * NOTE: consider renaming to "execute()"
	 */
	public Message doAction() {
		System.out.println("Step.doAction()");
		return action[0].execute();
		
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to add an Action to the set of actions for the Task
	 * @param act - the action to add to the list of actions
	 */
	public void setAction(Action act) {
		// TODO - implement Task.setAction
		throw new UnsupportedOperationException();
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is a setter to set the success attribute (successCriteria)
	 * @param success - the successCriteria to set the success attribute
	 */
	public void setSuccess(SuccessCriteria success) {
		this.success = success;
	}   // end Task.doAction

}   // end class Task