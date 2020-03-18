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

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * The SeqWF class is the workflow class for Sequences (runs) to be processed.  It specializes Workflow
 * 
 * @author John Hetrick
 */
public class SeqWF extends Workflow {

	/**
	 * The runWFID, or group workflow ID, is a unique value to identify the workflow
	 * 
	 * NOTE: Should probably DEPRECATE this and only use the parent class wfID attribute
	 */
	private String seqWorkflowID;

	/**
	 * This is the constructor for the RunWF class
	 */
	public SeqWF() {
		super();
		System.out.println("RunWF() constructor");
		
		this.setSeqWorkflowID("Run-ID");
		this.setCurrentStep(null);
		
	}   // end RunWF() constructor

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is another constructor for the RunWF class.  It takes a DataSet and a
	 * WorkflowDescription to bind together in the Workflow
	 * @param id - the id of the workflow
	 * @param set - the DataSet to bind with the WorkflowDescription in the Workflow
	 * @param description - the DataSet to bind with the WorkflowDescription in the Workflow
	 */
	public SeqWF(String id, DataSet set, WorkflowDescription description) {
		super(id, set, description);
		System.out.println("RunWF(DataSet set, WorkflowDescription description) constructor");
		
		this.setSeqWorkflowID(id);
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the seqWorkflowID attribute
	 * @return - the seqWorkflowID attribute
	 */
	public String getSeqWorkflowID() {
		return super.getWorkflowID();
	}   // end RunWF.getRunID()

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the seqWorkflowID attribute
	 * @param id - the ID to use in setting the seqWorkflowID attribute
	 */
	public void setSeqWorkflowID(String id) {
		super.setWorkflowID(id);
		//this.setWfID(seqWorkflowID);
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
		//this.setCurrentStep(this.getProcedure().nextTask(this.getCurrentStep()));
		
		Message msgOut = this.getCurrentStep().execute();
		System.out.println("   msgOut: " + msgOut.toString());
		
		return msgOut;
		
	}

}   // end class RunWF