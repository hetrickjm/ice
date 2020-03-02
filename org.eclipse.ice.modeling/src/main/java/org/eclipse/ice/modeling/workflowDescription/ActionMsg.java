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

	private Message msg;

	public ActionMsg() {
		System.out.println("ActionMsg() constructor");
		
		this.setMsg(new Message());
		this.msg.setMsgType("Default ActionMsg");
	}

	public Message getMsg() {
		return this.msg;
	}

	/**
	 * 
	 * @param msg
	 */
	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public Message execute() {
		System.out.println("ActionMsg.execute()");
		return msg;
	}

}   // end class ActionMsg