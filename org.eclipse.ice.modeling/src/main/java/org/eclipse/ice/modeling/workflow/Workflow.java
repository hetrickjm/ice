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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(Workflow.class);
	
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
	 * The workflowDescription attribute holds the WorkflowDescription associated with the workflow
	 */
	private WorkflowDescription workflowDescription;

	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The dataSet attribute holds the Data Set associated with the workflow
	 */
	private DataSet dataSet;

	/**
	 * DEPRECATE!
	 * 
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EXPLORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The wfState attribute holds the current state of the workflow
	 */
	private WorkflowState workflowStatus;

	/**
	 * The workflowStatusTablet attribute holds the status(es) for the workflow and
	 * the status of its tasks.  For a Sequence workflow there will
	 * only be one WorkflowStatus in the set.  However, a Group will contain
	 * multiple WorkflowStatuses - one for each sequence it contains.  Currently
	 * the tasks for a group apply to and must be executed on each sequence.  Hence the
	 * need for a set of WorkflowStatuses.  The status for each group Task set is tracked
	 * in the context of each sequence.  Note this is still a group level status for each
	 * Group Task.  The sequence has its own workflow with its own Tasks to be executed
	 * as a childWorkflow
	 */
	private List<WorkflowStatus> workflowStatusTable = null;

	/**
	 * This is the constructor for the Workflow class
	 */
	public Workflow() {
		logger.debug("Workflow() constructor");
		
		// initialize the attributes
		this.workflowStatus = WorkflowState.NOT_STARTED;
		this.workflowID = "Workflow-ID";
		this.workflowStatusTable = new ArrayList <WorkflowStatus>();
	}   // end Workflow() constructor

	/**
	 * This is another constructor for the Workflow class.  It takes the DataSet and
	 * WorkflowDescription that need to be bound together for the Workflow
	 * @param id - the id of the workflow
	 * @param set - the DataSet to bind with the WorkflowDescription in the Workflow
	 * @param description - the WorkflowDescription to bind with the DataSet in the Workflow
	 */
	public Workflow(String id, DataSet set, WorkflowDescription description) {
		logger.debug("Workflow(DataSet set, WorkflowDescription description) constructor");
		
		List <TaskStatus> taskStatusTable = null;
		
		this.workflowStatus = WorkflowState.NOT_STARTED;
		this.workflowID = id;
		this.dataSet    = set;
		this.workflowDescription  = description;
		this.workflowStatusTable = new ArrayList <WorkflowStatus>();
		
		/**
		 * Create the add an initial WorkflowStatus entry to the WorkflowStatusTable for
		 * the set of Tasks in the WorkflowDescription
		 */
		WorkflowStatus workflowStatus = new WorkflowStatus();
		taskStatusTable = workflowStatus.getTaskStatusTable();
		for (int i = 0; i < description.getNumberOfTasks(); i++) {
			// Create a new TaskStatus with the Task from the WorkflowDescription and
			// add it to the taskStatusTable
			taskStatusTable.add(new TaskStatus(description.getTask(i)));
		}
		
		this.workflowStatusTable.add(workflowStatus);
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
		int i   = -1,
			len = -1;
		List <TaskStatus> taskStatusTable = new ArrayList <TaskStatus>();
		
		// Set the workflowDescription attributed
		this.workflowDescription = wfd;
		
		/**
		 * Create and add TaskStatus entries to the new taskStatusTable for every Task
		 * in the WorkflowDescription
		 */
		for ( i = 0; i < wfd.getNumberOfTasks(); i++) {
			// Create a new TaskStatus with the Task from the WorkflowDescription and
			// add it to the taskStatusTable
			taskStatusTable.add(new TaskStatus(wfd.getTask(i)));
		}
		
		// Check if the workflowStatusTable needs to be created
		if (this.workflowStatusTable == null) {
			this.workflowStatusTable = new ArrayList <WorkflowStatus>();
		}
		
		len = this.workflowStatusTable.size();
		
		// If there are no entries in the workflowStatusTable create an initial entry
		if (len <= 0) {
			// create an initial WorkflowStatus in the WorkflowStatusTable
			this.workflowStatusTable.add(new WorkflowStatus());
			len = 1;
		}
		
		// For each WorkflowStatus in the table replace the old taskStatusTable
		// with the new taskStatusTable.  Note all workflows should have the same
		// set of Tasks.
		for (i = 0; i < len; i++) {
			this.workflowStatusTable.get(i).setTaskStatusTable(taskStatusTable);
			this.workflowStatusTable.get(i).setWorkflowState(WorkflowState.NOT_STARTED);
		}
			
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the workflowStatusSet attribute
	 */
	public List <WorkflowStatus> getWorkflowStatusTable() {
		return this.workflowStatusTable;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the workflowStatusSet attribute
	 * @param set
	 */
	public void setWorkflowStatusTable(List <WorkflowStatus> set) {
		this.workflowStatusTable = set;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning a workflowStatus from the
	 * workflowStatusTable attribute
	 * @param index - the index into the workflowStatusTable for the specific workflowStatus
	 * to be returned
	 */
	public WorkflowStatus getWorkflowStatus(int index) {
		return this.workflowStatusTable.get(index);
	}

	/**
	 * DEPRECATE!
	 * 
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the workflowStatus attribute
	 */
	public WorkflowState getWorkflowStatus() {
		return this.workflowStatus;
	}

	/**
	 * DEPRECATE!
	 * 
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the workflowStatus attribute
	 * @param wfs
	 */
	public void setWorkflowStatus(WorkflowState wfs) {
		this.workflowStatus = wfs;
	}

	/**
	 * NEEDS TO BE MODIFIED FOR NEW STATUS MECHANISM
	 * 
	 * This method evaluates if the workflow is complete or not.  It returns true if it is complete
	 * or false if not complete.
	 * 
	 * Note: Need to figure out how to handle the ERROR state.
	 */
	public boolean isComplete() {
		logger.debug("Workflow.isComplete()");
		
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
		logger.debug("Workflow.handleMsg(Message msgIn)\n\tmsgIn: ", msgIn.toString());
		
		Message msgOut = new Message();
		msgOut.setMsgType("Message out from WF");
		
		logger.debug("\tmsgOut: ", msgOut.toString());
		
		return msgOut;
	}

}   // end Workflow class