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

import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflowDescription.*;
import org.eclipse.ice.modeling.workflowDescription.tasks.*;
import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.states.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * Workflow is the entity that is responsible for managing the processing of a data set.
 * It is an abstract class which will be instantiated by a concrete class.  It is part
 * of composition pattern so clients only work with the Workflow interface and do not
 * need to know anything about the concrete classes or their interfaces.
 * 
 * @author John Hetrick
 */
public class Workflow {

	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The wfState attribute holds the current state of the workflow
	 */
	private WorkflowState workflowStatus;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The status attribute holds the current state of the workflow
	 */
	private List <TaskStatus> taskStatusTable = null;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The workflowID attribute holds the ID of the workflow
	 */
	private String workflowID;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The dataSet attribute holds the Data Set associated with the workflow
	 */
	private DataSet dataSet;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The workflowDescription attribute holds the WorkflowDescription associated with the workflow
	 */
	private WorkflowDescription workflowDescription;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The currentStep attribute holds the current step of the WorkflowDescription 
	 * the workflow is currently on
	 */
	private Task currentStep;

	/**
	 * This is the constructor for the Workflow class
	 */
	public Workflow() {
		System.out.println("Workflow() constructor");
		
		// initialize the attributes
		this.workflowStatus = WorkflowState.NOT_STARTED;
		this.workflowID = "Workflow-ID";
		this.taskStatusTable = new ArrayList <TaskStatus>();
	}   // end Workflow() constructor

	/**
	 * This is another constructor for the Workflow class.  It takes the DataSet and
	 * WorkflowDescription that need to be bound together for the Workflow
	 * @param id - the id of the workflow
	 * @param set - the DataSet to bind with the WorkflowDescription in the Workflow
	 * @param description - the WorkflowDescription to bind with the DataSet in the Workflow
	 */
	public Workflow(String id, DataSet set, WorkflowDescription description) {
		System.out.println("Workflow(DataSet set, WorkflowDescription description) constructor");
		
		this.workflowStatus = WorkflowState.NOT_STARTED;
		this.workflowID = id;
		this.dataSet    = set;
		this.workflowDescription  = description;
		this.taskStatusTable = new ArrayList <TaskStatus>();
		
		/**
		 * Creat the add TaskStatus entries to the taskStatusTable for every Task in the
		 * WorkflowDescription
		 */
		for (int i = 0; i < description.getNumberOfTasks(); i++) {
			// Create a new TaskStatus with the Task from the WorkflowDescription and
			// add it to the taskStatusTable
			this.taskStatusTable.add(new TaskStatus(description.getTask(i)));
		}
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the getWfID attribute
	 * 
	 * @return String - the workflowID attribute
	 */
	public String getWorkflowID() {
		System.out.println("Workflow.getWorkflowID()");
		return this.workflowID;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the getWfID attribute
	 * @param id  - id to use in setting the setWfID attribute
	 */
	public void setWorkflowID(String id) {
		System.out.println("Workflow.setWorkflowID(String id)");
		this.workflowID = id;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the dataSet attribute
	 * 
	 * @return DataSet
	 */
	public DataSet getDataSet() {
		System.out.println("Workflow.getDataSet()");
		return dataSet;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the dataSet attribute
	 * @param data - data is the Data Set to use to set the dataSet attribute
	 * 
	 * @return void
	 */
	public void setDataSet(DataSet set) {
		this.dataSet = set;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the workflowDescription attribute
	 * 
	 * @return WorkflowDescription
	 */
	public WorkflowDescription getWorkflowDescription() {
		System.out.println("Workflow.getWorkflowDescription()");
		return this.workflowDescription;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the workflowDescription attribute
	 * @param wfd - wfd is the used to set the procedure attribute
	 * 
	 * @return void
	 */
	public void setWorkflowDescription(WorkflowDescription wfd) {
		System.out.println("Workflow.setWorkflowDescription(WorkflowDescription wfd)");
		this.workflowDescription = wfd;
		
		/**
		 * Since the WorkflowDescription is being added, need to set/reset the taskStatusTable
		 * to match the WorkflowDescription.
		 */
		
		// First check if the taskStatusTable needs to be created or reset
		if (this.taskStatusTable == null) {
			this.taskStatusTable = new ArrayList <TaskStatus>();
		}
		else {
			// reset
			this.taskStatusTable.clear();
		}
		
		/**
		 * Creat the add TaskStatus entries to the taskStatusTable for every Task in the
		 * WorkflowDescription
		 */
		for (int i = 0; i < wfd.getNumberOfTasks(); i++) {
			// Create a new TaskStatus with the Task from the WorkflowDescription and
			// add it to the taskStatusTable
			this.taskStatusTable.add(new TaskStatus(wfd.getTask(i)));
		}
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the currentStep attribute
	 * 
	 * @return Task
	 */
	public Task getCurrentStep() {
		return currentStep;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the currentStep attribute
	 * @param step - step is used to set the currentStep attribute
	 * 
	 * @return void
	 */
	public void setCurrentStep(Task step) {
		this.currentStep = step;
	}

	/**
	 * This is a getter method to return the taskStatusTable attribute.  Note this is primarily
	 * for the child classes to use.
	 * 
	 * @return List <TaskStatus>
	 */
	protected List <TaskStatus> getTaskStatusTable() {
		System.out.println("Workflow.getTaskStatusTable()");
		//this.taskStatusTable.get(index);
		return this.taskStatusTable;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the workflowStatus attribute
	 */
	public WorkflowState getWorkflowStatus() {
		System.out.println("Workflow.getWorkflowStatus()");
		
		return this.workflowStatus;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the workflowStatus attribute
	 * @param workflowStatus - state (enum) to use to set the workflowStatus attribute
	 */
	public void setWorkflowStatus(WorkflowState wfs) {
		System.out.println("Workflow.setWorkflowStatus(WorkflowState workflowStatus)");
		this.workflowStatus = wfs;
	}

	/**
	 * This method evaluates if the workflow is complete or not.  It returns true if it is complete
	 * or false if not complete.
	 * 
	 * Note: Need to figure out how to handle the ERROR state.
	 */
	public boolean isComplete() {
		System.out.println("Workflow.isComplete()");
		
		if (this.workflowStatus == WorkflowState.COMPLETE)
			return true;
		
		return false;
	}

	/**
	 * This method is invoked by the workflow engine to have the workflow process an
	 * incoming message or action
	 * 
	 * This is the method from should be is overridden.
	 * 
	 * @param msgIn - msgIn is the incoming message to be recognized and then to take
	 * action on
	 * 
	 * @return Message
	 */
	public Message handleMsg(Message msgIn) {
		System.out.println("Workflow.handleMsg()");
		System.out.println("   msgIn: " + msgIn.toString());
		
		Message msgOut = new Message();
		msgOut.setMsgType("Message out from WF");
		
		System.out.println("   msgOut: " + msgOut.toString());
		
		return msgOut;
	}

}   // end Workflow class 