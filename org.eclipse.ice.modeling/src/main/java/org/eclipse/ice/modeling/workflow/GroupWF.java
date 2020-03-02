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
	 * The groupWFID, or group workflow ID, is a unique value to identify the workflow
	 * 
	 * NOTE: Should probably DEPRECATE this and only use the parent class wfID attribute
	 */
	private String groupWFID;
	
	/**
	 * The childWFSet attribute holds the set of child workflows that the 
	 * GroupWF can contain.  These child workflows are anticipated to be RunWF.
	 * 
	 * NOTE: Currently this attribute us being initialized to 2.  In the future
	 * this should be a dynamic list who's nodes are created on when needed.
	 */
	private Workflow[] childWFSet = new Workflow[2];

	/**
	 * The childWFIndex is set to the array index of the latest child workflow.  -1
	 * means the array is empty and there are not child workflows
	 * 
	 * NOTE: This will likely be DEPRECATED when implementing a dynamic child
	 * workflow list.
	 */
	private int childWFIndex = -1;
	
	/**
	 * This is the constructor for the GroupWF class.
	 */
	public GroupWF() {
		super();
		System.out.println("RunSetWF() constructor");
		
		this.setGroupWFID("GRP-ID");
		super.setCurrentStep(null);
		
	}   // end RunSetWF() constructor

	/**
	 * This is another constructor for the GroupWF class. It takes a DataSet and WorkflowDescription
	 * parameters to bind together in the Workflow.
	 * @param set  - the DataSe to bind with the WorkflowDescription in the Workflow
	 * @param description  - the WorkflowDescription to bind with the DataSet in the Workflow
	 */
	public GroupWF(DataSet set, WorkflowDescription description) {
		super(set, description);
		System.out.println("RunSetWF(DataSet set, WorkflowDescription description) constructor");
		
		// create the workflow ID from the GroupID in the MetaData
		String id = set.getMetaData().getGroupID() + "WF";
		this.setGroupWFID(id);
		super.setCurrentStep(null);
	
	}   // end RunSetWF.handleMsg(Message msgIn)

	/**
	 * This is a getter method to return the  attribute
	 * @return String - the groupID
	 */
	public String getGroupWFID() {
		return this.groupWFID;
	}   // end RunSetWF.getgroupID()

	/**
	 * This is a setter method to set the  attribute
	 * @param id  - id to use in setting the  attribute
	 */
	public void setGroupWFID(String id) {
		this.groupWFID = id + "WF";
		super.setWfID(groupWFID);
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
	 * This is a setter method to add a child workflow to the childWfs attribute.
	 * 
	 * @param childWFs
	 * 
	 * @return void
	 */
	public void addChildWF(Workflow workflow) {
		
		// If the index is any negative number it means there are not child workflows
		if (childWFIndex < 0) {
			this.childWFSet[0] = workflow;
		}
		else if (childWFIndex < 1) {
			this.childWFSet[this.childWFIndex] = workflow;
			this.childWFIndex++;
		}
		// else do nothing.  Need to think about how to throw an error
		// however this is a temporary implementation for the Prototype
		
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

}   // end class RunSetWF