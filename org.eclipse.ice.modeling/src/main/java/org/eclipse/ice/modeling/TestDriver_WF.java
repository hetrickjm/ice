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
package org.eclipse.ice.modeling;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.actors.*;

/**
 * The TestDrive_WF class is simply a test fixture to do some initial testing and system invocation during development
 * 
 * @author John Hetrick
 */
public class TestDriver_WF {
	/**
	 * This is the main method that is run to start the program execution.
	 * 
	 * @author John Hetrick
	 * @param args This parameter potentially holds a set of argument that are passed to the program when it is invoked.  For this test fixture there are currently no parameters passed in.
	 */
	public static void main(String[] args) {
		System.out.println("TestDriver_WF.main()");

		//Instantiate all supporting objects and kick off the workflow system
		TransSrvcStub  transSrvc = new TransSrvcStub();
		ReducerStub	   reducer1  = new ReducerStub();
		
		// Creating the Workflow System creates all of its parts includign the engine
		// which is the part that needs to be called byt the Translation Service
		WorkflowSystem wf = new WorkflowSystem( transSrvc, reducer1 );
		
		transSrvc.setWorkflow(wf.getWorkflowEngine());
		transSrvc.generateCmnd("Transition Service Command");
		
		
	}  // end main()
}