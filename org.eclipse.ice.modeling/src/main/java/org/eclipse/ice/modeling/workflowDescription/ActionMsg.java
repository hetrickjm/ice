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

/**
 * The ActionMsg class is a type of Action.  This specialization of Action is specifically to send a message.  For the AR Workflow System this would be the messages that are sent to the reducers to process a Data Set.  It should be possible to instantiate ActionMsgs during run time as the behavior for sending a message should not change, just the message to be sent.
 * 
 * @author John Hetrick
 */
public class ActionMsg extends Action {

	/**
	 * The msg attribute contains the message that is to be set out as
	 * the invocation of this Action.
	 */
	private Message msg;

	/**
	 * This is the constructor for the ActionMsg class
	 */
	public ActionMsg() {
		System.out.println("ActionMsg() constructor");
		
		this.setMsg(new Message());
		this.msg.setMsgType("Default ActionMsg");
	}

	/**
	 * This is another constructor for the ActionMsg class that takes the message 
	 * that will be sent out when the class is invoked.
	 * 
	 * @param sendMsg - the Message to use in setting the sendMsg attribute
	 */
	public ActionMsg(Message sendMsg) {
		System.out.println("ActionMsg(Message sendMsg) constructor");
		
		this.setMsg(sendMsg);
	}

	/**
	 * This is a getter method to return the msg attribute
	 */
	public Message getMsg() {
		return this.msg;
	}

	/**
	 * This is a setter method to set the msg attribute
	 * @param msg - Message to use to set the msg attribute
	 */
	public void setMsg(Message msg) {
		this.msg = msg;
	}

	/**
	 * The execute method overrides the execute method from the parent class.  This method is invoked to activate or execute the action specified in this class/object
	 */
	public Object execute() {
		System.out.println("ActionMsg.execute()");
		return this.msg;
	}

}   // end class ActionMsg