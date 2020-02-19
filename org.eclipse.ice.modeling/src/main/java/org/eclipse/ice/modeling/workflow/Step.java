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
package org.eclipse.ice.modeling.workflow;

import org.eclipse.ice.modeling.workflowEngine.*;

public class Step {

	private Action action;
	private SuccessCriteria success;

	public Step() {
		System.out.println("Step() constructor");
		
		// set an inital default action.
		action = new ActionMsg();
		success = new SuccessCriteria ( "Step Success Criteria" );
		
	}   // end Step() constructor;

	/**
	 * 
	 * @return the msg
	 */
	public Action getAction() {
		return this.action;
	}   // end Step.getAction

	/**
	 * 
	 * @param act the msg to set
	 */
	public void setAction(Action act) {
		this.action = act;
	}   // end Step.setAction

	/**
	 * 
	 * @return the success
	 */
	public SuccessCriteria getSuccess() {
		System.out.println("Step.getSuccess()");
		return success;
	}   // end Step.getSuccess()

	/**
	 * 
	 * @param success the success to set
	 */
	public void setSuccess(SuccessCriteria success) {
		this.success = success;
	}   // end Step.setSuccess(SuccessCriteria success)

	public Message doAction() {
		System.out.println("Step.doAction()");
		return action.execute();
		
	}   // end Step.doAction

}   // end class Step