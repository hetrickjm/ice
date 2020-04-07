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
package org.eclipse.ice.modeling.workflowDescription.action;

import org.eclipse.ice.modeling.workflowEngine.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * The Action class is the class that provides the actual behavior to a Task.  An action should
 * be atomic in nature.  Currently the thinking is that this is an abstract class that must be
 * specialized for some specific action e.g. sending a message.  More work is needed to
 * flesh this out.
 * 
 * @author John Hetrick
 */
public class Action {

	/**
	 * This is the constructor for the Action class
	 */
	public Action() {
		System.out.println("Action() constructor");
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is the primary method to make the action take action (execute).
	 * 
	 * I think this should be virtual or abstract function so it has to be implemented by
	 * the concrete class.
	 */
	public Object execute() {
		System.out.println("Action.execute()");
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is the primary method to make the action take action (execute).
	 * 
	 * I think this should be virtual or abstract function so it has to be implemented by
	 * the concrete class.
	 * @param obj - a generic parameter to pass in required information
	 */
	public Object execute(Object obj) {
		System.out.println("Action.execute(Object obj)");
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is the primary method to make the action take action (execute).
	 * 
	 * I think this should be virtual or abstract function so it has to be implemented by
	 * the concrete class.
	 * @param arg1 - a generic parameter to pass in required information
	 * @param arg2 - this is a parameter that can be used for anything, but is expected to be an incoming message
	 */
	public Object execute(Object arg1, Object arg2) {
		// TODO - implement Action.execute
		throw new UnsupportedOperationException();
	}

}