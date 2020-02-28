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
import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflowDescription.*;

public class RunWF extends Workflow {

	private String runID;

	public RunWF() {
		super();
		System.out.println("RunWF() constructor");
		
		this.setRunID("Run-ID");
		
		this.setCurrentStep(null);
		
	}   // end RunWF() constructor

	public String getRunID() {
		return this.runID;
	}   // end RunWF.getRunID()

	/**
	 * 
	 * @param id
	 */
	public void setRunID(String id) {
		this.runID = id;
		this.setWfID(runID);
	}   // end RunWF.setRunID(String id)

	/**
	 * This method is invoked by the workflow engine to have the workflow process an
	 * incoming message or action
	 * 
	 * This is the method from overides the same method of the parent class, Workflow.
	 * 
	 * @param msgIn - msgIn is the incoming message to be recognized and then to take
	 * action on
	 */
	public Message handleMsg(Message msgIn) {
		System.out.println("RunWF.nextMsg()");
		System.out.println("   msgIn: " + msgIn.toString());
		
		// createa an out message.  Keep the same attributes as in except the command
		// Message msgOut = new Message();
		// msgOut.setExpId( msgIn.getExpId());
		// msgOut.setSrcInstrument(msgIn.getSrcInstrument());
		// msgOut.setCmnd("msgOut: Message out from RunWF");
		
		
		// Get the next step from the procedure
		// If it is the first run we should get step 0
		this.setCurrentStep(this.getProcedure().nextTask(this.getCurrentStep()));
		
		Message msgOut = this.getCurrentStep().doAction();
		System.out.println("   msgOut: " + msgOut.toString());
		
		return msgOut;
		
	}

	/**
	 * This is another constructor for the RunWF class.  It takes a DataSet and a Workflow description to bind together in the Workflow
	 * @param set  - the DataSet to bind with the WorkflowDescription in the Workflow
	 * @param description  - the DataSet to bind with the WorkflowDescription in the Workflow
	 */
	public RunWF(DataSet set, WorkflowDescription description) {
		// TODO - implement RunWF.RunWF
		throw new UnsupportedOperationException();
	}   // end RunWF.nextMsg

}   // end class RunWF