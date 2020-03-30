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
import org.eclipse.ice.modeling.workflowEngine.Message;

/**
 * The ActionGroupReduce class holds the details of invoking a child workflow to reduce a specific sequence.
 * 
 * @author John Hetrick
 */
public class GroupReduceAction extends GroupAction {

	/**
	 * This is the constructor for the ActionGroupReduce class
	 */
	public GroupReduceAction() {
		super();
		System.out.println("ActionGroupReduce() constructor");
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is another constructor for the ActionGroupReduce class that take in an InstructionType
	 * @param instr - specifies the type of group action
	 */
	public GroupReduceAction(InstructionGroup instr) {
		super(instr);
		System.out.println("ActionGroupReduce() constructor");
	}

	/**
	 * The execute method is responsible for performing the action when invoked.
	 */
	public Object execute() {
		// TODO - implement ActionGroupReduce.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * This execute method is responsible for performing the action when invoked and takes a parameter and returns an object.
	 * @param obj - generic parameter used to pass in information needed to complete the action
	 */
	public Object execute(Object obj) {
		System.out.println("ActionGroupReduce.execute(Workflow workflow, Object obj)");
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is the primary method to make the action take action (execute).
	 * @param arg1 - for this class this parameter is expected to be a child Workflow which
	 *    is a SeqWF but can be cast as the more generic Workflow
	 * @param arg2 - this is a parameter that can be used for anything, but is 
	 *    expected to be an incoming message
	 */
	public Object execute(Object arg1, Object arg2) {
		System.out.println("ActionGroupReduce.execute(Object arg1, Object arg2)");
		
		Workflow childWF = (Workflow) arg1;
		Message msgIn = (Message) arg2,
				msgOut = null;
		
		msgOut = childWF.handleMsg(msgIn);
		
		return msgOut;
		
	}

}