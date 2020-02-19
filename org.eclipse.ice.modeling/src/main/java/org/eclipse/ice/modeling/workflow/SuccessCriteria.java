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

public class SuccessCriteria {

	private String successMsg;
	
	public SuccessCriteria() {
		System.out.println("SuccessCriteria() constructor");
		
		this.setSuccessMsg("Default SuccessCriteria");
	}   // end SuccessCriteria() constructor

	/**
	 * 
	 * @param msg
	 */
	public SuccessCriteria(String msg) {
		System.out.println("SuccessCriteria(String msg) constructor");
		
		this.setSuccessMsg(msg);
	}   // end SuccessCriteria(String msg) constructor

	public String getSuccessMsg() {
		System.out.println("SuccessCriteria.getSuiccessMsg()");
		return this.successMsg;
	}

	/**
	 * 
	 * @param msg
	 */
	public void setSuccessMsg(String msg) {
		System.out.println("SuccessCriteriaset.SuccessMsg(String msg)");
		this.successMsg = msg;
	}

}   // end class SuccessCriteria