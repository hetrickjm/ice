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
package org.eclipse.ice.modeling.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.ice.modeling.IWorkflow;
import org.eclipse.ice.modeling.workflowEngine.*;

/**
 * The ReducerStub is a class that acts like a Reducer system.  It is a stub for testing.
 * 
 * @author John Hetrick
 */
public class ReducerStub {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReducerStub.class);
	
	public ReducerStub() {
		System.out.println("ReducerStub() constructor");
		
	}   // end ReducerStub() construcor

	/**
	 * 
	 * @param msg
	 * @param msgSrc
	 */
	public void processMessage(Message msg, IWorkflow msgSrc) {
		System.out.println("ReducerStub.processMessage()");
		
		// Process the incoming message - Reduction Action
		System.out.println("   msg: " + msg.toString());
		
		// Send a response to the msgSrc based on processing 
		
	}   // end ReducerStub.processMessage()


}   // end class ReducerStub