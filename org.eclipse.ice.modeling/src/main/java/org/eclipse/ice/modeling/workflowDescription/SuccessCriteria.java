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
public class SuccessCriteria {

	/**
	 * CURRENTLY THIS ATTRIBUTE IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * The successMsg attribute is the success (completion) criteria
	 */
	private String successMsg;
	
	/**
	 * This is the constructor for the CompletionCriteria class
	 */
	public SuccessCriteria() {
		System.out.println("SuccessCriteria() constructor");
		
		this.setSuccessMsg("Default SuccessCriteria");
	}   // end SuccessCriteria() constructor

	/**
	 * This is another method for the SuccessCriteria Class.  This constructor takes a
	 * messages string to initialize the successMsg attribute.
	 * @param msg  - the message string to initialize the successMsg attribute to
	 */
	public SuccessCriteria(String msg) {
		System.out.println("SuccessCriteria(String msg) constructor");
		
		this.setSuccessMsg(msg);
	}   /**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the successMsg attribute
	 */

	public String getSuccessMsg() {
		System.out.println("SuccessCriteria.getSuiccessMsg()");
		return this.successMsg;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the successMsg attribute
	 * @param msg  - the message to set the successMsg attribute
	 */
	public void setSuccessMsg(String msg) {
		System.out.println("SuccessCriteriaset.SuccessMsg(String msg)");
		this.successMsg = msg;
	}

}   // end class SuccessCriteria