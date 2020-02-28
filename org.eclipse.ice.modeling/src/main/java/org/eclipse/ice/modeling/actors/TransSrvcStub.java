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


import org.eclipse.ice.modeling.IWorkflow;
import org.eclipse.ice.modeling.workflowEngine.*;

/**
 * The TransSrvcStub is a class to act as the Translation Service that would send messages to the AR Workflow system.  It is a stub for testing.
 * 
 * @author John Hetrick
 */
public class TransSrvcStub {

	private IWorkflow workflow;   // end TransSrvcStub() constructor
	

	public TransSrvcStub() {
		System.out.println("TransSrvcStub() constructor");
		
	}   // endTransSrvcStub() constructor

	public IWorkflow getWorkflow() {
		System.out.println("TransSrvcStub.getWorkflow()");
		return this.workflow;
		
	}   // end TransSrvcStub.getWorkflow()

	/**
	 * 
	 * @param wf
	 */
	public void setWorkflow( IWorkflow wf ) {
		System.out.println("TransSrvcStub.setWorkflow( IWorkflow workflow )");
		this.workflow = wf;
		
	}   // end TransSrvcStub.setWorkflow(WorkflowEngine workflow)

	public void generateCmnd() {
		System.out.println("TransSrvcStub.generateCmnd()");
		
		// Create a Message and send it to the Workflow
		Message msg = new Message();
		msg.setCommand("Default msg from Transition Service");
		
		System.out.println("   cmnd: " + msg.getCommand());

		workflow.handleMsg(msg);
		
	}   // end TransSrvcStub.generateCmnd()

	/**
	 * 
	 * @param cmnd - The command for the message that is to be sent
	 */
	public void generateCmnd(String cmnd) {
		System.out.println("TransSrvcStub.generateCmnd(String cmnd)");
		
		// Create a Message and send it to the Workflow
		Message msg = new Message();
		msg.setCommand(cmnd);
		
		// Create the Meta Data for the Data Set that is to be part of the msg
		MetaData meta = new MetaData("Inst-1", "Exp-1", "RunSet-1", "Run-1");
		
		// Create the Data Set and with the Meta Data
		DataSet dataSet = new DataSet(meta, "DS-42");
		
		// Add the Data Set to the message
		msg.setDataSetRef(dataSet);

		System.out.println("   " + msg.toString());

		workflow.handleMsg(msg);
		
	}   // end TransSrvcStub.generateCmnd(String cmnd)


}   // end class TransSrvcStub