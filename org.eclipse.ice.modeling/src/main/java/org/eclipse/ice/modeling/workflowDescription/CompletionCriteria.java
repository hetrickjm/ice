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
 * This class represents (holds) the completion criteria for a WorkflowDescription.
 * 
 * NOTE: At this time this is a concept that is believed to be needed, but the detail has yet to be
 * worked out.
 * 
 * @author John Hetrick
 */
public class CompletionCriteria {

	/**
	 * CURRENTLY THIS ATTRIBUTE IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * The completionMsg attribute is the completion criteria
	 */
	private String completionMsg;

	/**
	 * This is the constructor for the CompletionCriteria class
	 */
	public CompletionCriteria() {
		System.out.println("CompleteCriteria() constructor");
		
		this.setCompletionMsg("Default Completion Criteria");
	}

	/**
	 * This is another method for the CompletionCriteria Class.  This constructor takes a
	 * messages string to initialize the completionMsg attribute.
	 * @param msg  - the message string to initialize the completionMsg attribute
	 */
	public CompletionCriteria(String msg) {
		System.out.println("CompleteCriteria(String msg) constructor");
		
		this.setCompletionMsg(msg);
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the completionMsg attribute
	 */
	public String getCompletionMsg() {
		System.out.println("CompleteCriteria.getcompletionMsg()");
		return this.completionMsg;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the completionMsg attribute
	 * @param msg  - the message to set the completionMsg to
	 */
	public void setCompletionMsg(String msg) {
		System.out.println("CompleteCriteria.setcompletionMsg(String msg)");
		this.completionMsg = msg;
	}

}   // end class CompleteCriteria