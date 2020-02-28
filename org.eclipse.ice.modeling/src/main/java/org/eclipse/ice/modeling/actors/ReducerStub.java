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

import org.eclipse.ice.modeling.workflowEngine.*;

/**
 * The ReducerStub is a class that acts like a Reducer system.  It is a stub for testing.
 * 
 * @author John Hetrick
 */
public class ReducerStub {

	public ReducerStub() {
		System.out.println("ReducerStub() constructor");
		
	}   // end ReducerStub() construcor

	/**
	 * 
	 * @param msg
	 * @param msgSrc
	 */
	public void processMessage(Message msg, WorkflowEngine msgSrc) {
		System.out.println("ReducerStub.processMessage()");
		
		// Process the incoming message - Reduction Action
		System.out.println("   msg: " + msg.getExpId() + ", Instrument: " + msg.getSrcInstrument() + ", cmnd: " + msg.getCmnd());
		
		// Send a response to the msgSrc based on processing 
		
	}   // end ReducerStub.processMessage()


}   // end class ReducerStub