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
 * The GroupWF specializes Workflow.  GroupWF is a workflow to process WorkflowDescriptions
 * designed to process groups of Data Sets (Sequences) Workflows.
 * 
 * @author John Hetrick
 */
public class GroupWF extends Workflow {

	/**
	 * The groupWF_ID, or group workflow ID, is a unique value to identify the workflow
	 */
	private String groupWF_ID;
	
	/**
	 * The childWFSet attribute holds the set of child workflows that the GroupWF can contain.  These child workflows are anticipated to be RunWF
	 */
	private Workflow[] childWFSet;

	public GroupWF() {
		super();
		System.out.println("RunSetWF() constructor");
		
		this.setGroupWF_ID("groupID-10");
		
		this.setCurrentStep(null);
		
	}   // end RunSetWF() constructor

	/**
	 * This is a getter method to return the groupWF_ID attribute
	 * @return String - the groupID
	 */
	public String getGroupWF_ID() {
		return groupWF_ID;
	}   // end RunSetWF.getgroupID()

	/**
	 * This is a setter method to set the groupWF_ID attribute
	 * @param id  - id to use in setting the groupWF_ID attribute
	 */
	public void setGroupWF_ID(String id) {
		this.groupWF_ID = id;
		this.setWfID(groupWF_ID);
	}   // end RunSetWF.setgroupID(String id)

	/**
	 * This is a getter method to return the childWfs attribute.
	 * 
	 * @return Workflow
	 */
	public Workflow[] getChildWFSet() {
		return this.childWFSet;
	}   // end RunSetWF.getChildWFs()

	/**
	 * This is a setter method to set the childWfs attribute.
	 * 
	 * @param childWFs
	 * 
	 * @return void
	 */
	public void addChildWF(Workflow workflow) {
		this.childWFSet[0] = workflow;
	}   // end RunSetWF.setChildWFs(Workflow childWFs)

	/**
	 * This method is invoked by the workflow engine to have the workflow process an
	 * incoming message or action
	 * 
	 * This is the method from overides the same method of the parent class, Workflow.
	 * 
	 * @param msgIn - msgIn is the incoming message to be recognized and then to take
	 * action on
	 * 
	 * @return Message - represents the action
	 */
	public Message handleMsg(Message msgIn) {
		System.out.println("RunSetWF.handleMsg()");
		System.out.println("   msgIn: " + msgIn.toString());
		
		// Next create the out message from the child (the run) not from the experiment
		// The experiment should never really generate a message only runs should
		// By doing this we can have the experiment intelligently process the incoming message if
		// there are multiple runs and they need to be processed in a specific order.
		// This is really for batch processing but will/should handle all cases
		
		Message msgOut = this.childWFSet[0].handleMsg(msgIn);
		System.out.println("   msgOut: " + msgOut.toString());
		
		return msgOut;
	}

	/**
	 * This is another constructor for the GroupWF class. It takes
	 * @param set  - the DataSe to bind with the WorkflowDescription in the Workflow
	 * @param description  - the WorkflowDescription to bind with the DataSet in the Workflow
	 */
	public GroupWF(DataSet set, WorkflowDescription description) {
		// TODO - implement GroupWF.GroupWF
		throw new UnsupportedOperationException();
	}   // end RunSetWF.handleMsg(Message msgIn)

}   // end class RunSetWF