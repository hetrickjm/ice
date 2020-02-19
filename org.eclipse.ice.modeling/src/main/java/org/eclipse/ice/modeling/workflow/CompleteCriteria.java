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

public class CompleteCriteria {

	private String compltMsg;

	public CompleteCriteria() {
		System.out.println("CompleteCriteria() constructor");
		
		this.setCompltMsg("Default Completion Criteria");
	}

	public CompleteCriteria(String msg) {
		System.out.println("CompleteCriteria(String msg) constructor");
		
		this.setCompltMsg(msg);
	}

	public String getCompltMsg() {
		System.out.println("CompleteCriteria.getCompltMsg()");
		return this.compltMsg;
	}

	/**
	 * 
	 * @param compltMsg
	 */
	public void setCompltMsg(String compltMsg) {
		System.out.println("CompleteCriteria.setCompltMsg(String compltMsg)");
		this.compltMsg = compltMsg;
	}

}   // end class CompleteCriteria