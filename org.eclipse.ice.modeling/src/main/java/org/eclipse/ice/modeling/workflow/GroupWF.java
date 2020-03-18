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

import java.util.*;

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
	private String groupWorkflowID;
	
	/**
	 * The childWFSet attribute holds the set of child workflows that the
	 * GroupWF can contain.  These child workflows are anticipated to be RunWF.
	 * 
	 * NOTE: Currently this attribute us being initialized to 2.  In the future
	 * this should be a dynamic list who's nodes are created on when needed.
	 */
	private List <Workflow> childWorkflowSet;

	/**
	 * The childWFIndex is set to the array index of the latest child workflow.  -1
	 * means the array is empty and there are not child workflows
	 * 
	 * NOTE: This will likely be DEPRECATED when implementing a dynamic child
	 * workflow list.
	 */
	private int childWorkflowIndex = -1;
	
	/**
	 * This is the constructor for the GroupWF class.
	 */
	public GroupWF() {
		super();
		System.out.println("GroupWF() constructor");
		
		this.setGroupWFID("GRP-ID");
		super.setCurrentStep(null);
		
	}   // end RunSetWF() constructor

	/**
	 * This is another constructor for the GroupWF class. It takes a DataSet and WorkflowDescription
	 * parameters to bind together in the Workflow.
	 * @param id - the ID of the workflow
	 * @param set - the DataSe to bind with the WorkflowDescription in the Workflow
	 * @param description - the WorkflowDescription to bind with the DataSet in the Workflow
	 */
	public GroupWF(String id, DataSet set, WorkflowDescription description) {
		super(id, set, description);
		System.out.println("GroupWF(STring id, DataSet set, WorkflowDescription description) constructor");

	}   // end RunSetWF.handleMsg(Message msgIn)

	/**
	 * This is a getter method to return the  attribute
	 * @return String - the groupID
	 */
	public String getGroupWFID() {
		System.out.println("GroupWF.getGroupWFID()");
		return this.groupWorkflowID;
	}   // end RunSetWF.getgroupID()

	/**
	 * This is a setter method to set the  attribute
	 * @param id  - id to use in setting the  attribute
	 */
	public void setGroupWFID(String id) {
		System.out.println("GroupWF.getGroupWFID()");
		this.groupWorkflowID = id + "WF";
		super.setWorkflowID(groupWorkflowID);
	}   // end RunSetWF.setgroupID(String id)

	/**
	 * This is a getter method to return the childWorkflowss attribute.
	 * @return Workflow
	 */
	public List <Workflow> getChildWFSet() {
		System.out.println("GroupWF.getChildWFSet()");
		return this.childWorkflowSet;
	}   // end RunSetWF.getChildWFs()

	/**
	 * This is a setter method to add a child workflow to the childWfs attribute.
	 * 
	 * @param childWFs
	 * 
	 * @return void
	 */
	public void addChildWF(Workflow workflow) {
		System.out.println("GroupWF.addChildWF(Workflow workflow)");
		this.childWorkflowSet.add(workflow);
		
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
		System.out.println("GroupWF.handleMsg(Message msgIn)");
		System.out.println("   msgIn: " + msgIn.toString());
		
		// Next create the out message from the child (the run) not from the experiment
		// The experiment should never really generate a message only runs should
		// By doing this we can have the experiment intelligently process the incoming message if
		// there are multiple runs and they need to be processed in a specific order.
		// This is really for batch processing but will/should handle all cases
		
		Message msgOut = this.childWorkflowSet[0].handleMsg(msgIn);
		System.out.println("   msgOut: " + msgOut.toString());
		
		return msgOut;
	}

}   // end class RunSetWF