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
import org.eclipse.ice.modeling.states.*;

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
	 * DEPRECATE:
	 * Use super class instead
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
	private List<Workflow> childWorkflowSet;

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
		
		// this.setGroupWorkflowID("---");
		this.childWorkflowSet = new ArrayList <Workflow>();
		super.setWorkflowStatus(WorkflowState.NOT_STARTED);
		
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
		
		this.childWorkflowSet = new ArrayList <Workflow>();
		super.setWorkflowStatus(WorkflowState.NOT_STARTED);

	}

	/**
	 * This is a getter method to return the  attribute
	 * @return String - the groupID
	 */
	public String getGroupWorkflowID() {
		System.out.println("GroupWF.getGroupWFID()");
		return this.groupWorkflowID;
	}

	/**
	 * This is a setter method to set the  attribute
	 * @param id  - id to use in setting the  attribute
	 */
	public void setGroupWorkflowID(String id) {
		System.out.println("GroupWF.getGroupWFID()");
		this.groupWorkflowID = id + "WF";
		super.setWorkflowID(groupWorkflowID);
	}
	
	/**
	 * This is a getter method to return the childWorkflowss attribute.
	 * @return List <Workflow>
	 */
	public List<Workflow> getChildWorkflowSet() {
		System.out.println("GroupWF.getChildWFSet()");
		return this.childWorkflowSet;
	}

	/**
	 * This is a getter method to return a single childWorkflows found in
	 * the childWorkflowSet.
	 * 
	 * @param key - String that is the key to find a specific child workflow.  The key consists of:
	 * 		key = meta.getInstrumentID() +
	 *			"/" + meta.getExperimentID() +
	 *			"/" + meta.getGroupID() +
	 *			"/WFS-" + meta.getSequenceNumber();
	 *
	 * @return Workflow
	 */
	public Workflow getChildWorkflowSet(String key) {
		System.out.println("GroupWF.getChildWorkflowSet(String key)");
		
		boolean done = false;
		Workflow wf = null;
		
		for (int i = 0;(i < this.childWorkflowSet.size() && !done); i++) {
			if (key == this.childWorkflowSet.get(i).getWorkflowID()) {
				wf = this.childWorkflowSet.get(i);
				done = true;
			}
		}
		
		return wf;
	}

	/**
	 * This is a setter method to add a child workflow to the childWfs attribute.
	 * 
	 * @param - the Workflow to add to the set of childWFs
	 * 
	 * @return void
	 */
	public void addChildWorkflow(Workflow workflow) {
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
		
		Message msgOut = null;
		int i = -1;
		boolean found = false;
		
		// Check the current status of the Group workflow and take appropriate action
		// Get the Sequence number the DataSet fo the message is related to.  The sequence
		// number is the indext to the set of child workflows
		int seqNum = msgIn.getDataSetRef().getMetaData().getSequenceNumber();
		
		switch (super.getWorkflowStatus()) {
			case NOT_STARTED: 
				// Which Task am I executing?
				
				// Invoke that task.  Not as that task is stateless, any information
				// it need must be passed into it.  Perhaps we need a switch
				// to process specific tasks
				// Get the first task
				List <TaskStatus> taskStatusTable = super.getTaskStatusTable();
				
				// To get the current task, loop through the tasks looking for the
				// first one not complete.  Consider making this a method
				// Task getTask()
				for (i = 0; (i < taskStatusTable.size() -1 && !found); i++) {
					if ( !(taskStatusTable.get(i).isComplete()))
						found = true;
				}
				
				if (found) {
					// Invoke the task
					msgOut = (Message) taskStatusTable.get(i).getTask().execute(this, msgIn);
				}
				/**
				// If found a task to execute
				if (found) {
					// Determine what the Task is:
					switch (taskStatusTable.get(i).getTask().g) {
					case ""
					}
				}
				 * 
				 */
				
				
				// do we need to check the state of the childWorkflow?
				//this.
				
				
				// Invoke the first task in the table
				// The tasks should take any necessary action.  In this case
				// It needs to invoke the child to handle the message
				//msgOut = taskStatusTable.get(0).getTask().execute();
				
				//Workflow cwf = this.childWorkflowSet.get(seqNum);
				//msgOut = cwf.handleMsg(msgIn);
				
				super.setWorkflowStatus(WorkflowState.IN_PROGRESS);
				break;
				
			case IN_PROGRESS: 
				break;
			
			case COMPLETE: 
				break;
			
			case ERROR: 
				break;
			
			default:
		
		}
		
		
		// Create key to find the appropriate child workflow
		
		// Find the child workflow
		
		// Invoke the child workflow to handle the message
		//Message msgOut = this.childWorkflowSet[0].handleMsg(msgIn);
		//System.out.println("   msgOut: " + msgOut.toString());
		
		return msgOut;
	}

}   // end class RunSetWF