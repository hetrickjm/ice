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

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED
 * 
 * This class represents (holds) the Success criteria for a Task.
 * 
 * NOTE: At this time this is a concept that is believed to be needed, but the detail has yet to be
 * worked out.  It may be that this and CompletionCriteria are the same thing.  One reason for this
 * is that for the current AR Workflow System some Task require multiple received messages before
 * they can be considered "successful".
 * 
 * @author John Hetrick
 */
public class MsgCompleteCriteria extends CompletionCriteria {

	/**
	 * CURRENTLY THIS ATTRIBUTE IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * The successMsg attribute is the success (completion) criteria
	 */
	private List<String> msgSet = null;
	
	/**
	 * This is the constructor for the CompletionCriteria class
	 */
	public MsgCompleteCriteria() {
		System.out.println("MsgsCriteria() constructor");
		
		msgSet = null;
		
		// this.setSuccessMsg("Default MsgsCriteria");
	}   // end MsgsCriteria() constructor

	/**
	 * This is another method for the MsgsCriteria Class.  This constructor takes a
	 * messages string to initialize the successMsg attribute.
	 * @param msg - the message string to initialize the successMsg attribute to
	 */
	public MsgCompleteCriteria(String msg) {
		System.out.println("MsgsCriteria(String msg) constructor");
		
		if (msg != null) {
			this.msgSet = new ArrayList();
			this.addMsg(msg);
		}
	}   
	
	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the msgSet attribute
	 */
	public List<String> getMsgSet() {
		System.out.println("MsgsCriteria.getSuiccessMsg()");
		return this.msgSet;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to add a msg (String) to the msgSet attribute
	 * @param msg - the message to set the successMsg attribute
	 */
	public void addMsg(String msg) {
		System.out.println("MsgsCriteria.addMsg(String msg)");
		
		// If there are no entries yet creat the Set
		if (this.msgSet == null)
			this.msgSet = new ArrayList();
		
		this.msgSet.add(msg);
	}

}   // end class MsgsCriteria