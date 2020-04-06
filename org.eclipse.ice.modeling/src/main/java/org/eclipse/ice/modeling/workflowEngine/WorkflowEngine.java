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
package org.eclipse.ice.modeling.workflowEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.ice.modeling.actors.*;
import org.eclipse.ice.modeling.*;
import org.eclipse.ice.modeling.workflow.*;
import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflowDescription.*;

/**
 * The WorkflowEngine is the class that handles the incoming messages, invoking
 * the correct workflow and managing whatever the workflow needs to do next.
 * Generally send a message to some external actor like a reducer
 * 
 * @author John Hetrick
 */
public class WorkflowEngine implements IWorkflow {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(WorkflowEngine.class);
	
	/**
	 * The reducer attribute holds a reference to a post processing reducer system
	 * that will handle the reduction of the data sets
	 */
	private ReducerStub reducer;
	
	/**
	 * THIS IS SIMPLY TESTING CONCEPTS
	 * 
	 * The workflow attribute is the workflow that the workflow engine is
	 * working on.  There are expected to be many workflows and the one 
	 * that the workflow needs to work with must be figured out and retrieved for it.
	 */
	private Workflow workflow;
	
	/**
	 * The workflowRepo attribute is the repository for all workflows
	 */
	private WorkflowRepo workflowRepo;
	
	/**
	 * The workflowDescriptionRepo attribute is the repository for 
	 * the set of WorkflowDescriptions
	 */
	private WorkflowDescriptionRepo workflowDescriptionRepo;

	/**
	 * This is the constructor for the WorkflowEngine class
	 */
	public WorkflowEngine() {
		logger.debug("WorkflowEngine() constructor");
		
		this.reducer = null;
		this.workflow = null;
		
	}   // end WorkflowEngine() constructor

	/**
	 * THIS CONSTRUCTOR IS PRIMARILY FOR TESTING CONCEPTS AND MAY BE DEPRECATED
	 * 
	 * This is another constructor for the WorkflowEngine class that takes a
	 * reference to a reducer and a workflow
	 * 
	 * @param reducer - Reference to a reducer system
	 * @param wfRepo - the Workflow repository
	 * @param wdRepo - the WorkflowDescription repository
	 */
	public WorkflowEngine(ReducerStub reducer, WorkflowRepo wfRepo, WorkflowDescriptionRepo wdRepo) {
		logger.debug("WorkflowEngine(ReducerStub reducer, WorkflowRepo wfRepo, WorkflowDescriptonRepo wdRepo) constructor");
		
		this.reducer                 = reducer;
		this.workflowRepo            = wfRepo;
		this.workflowDescriptionRepo = wdRepo;
		
	}   // end WorkflowEngine() constructor

	/**
	 * This is a getter method to return the reducer attribute
	 * 
	 * @return ReducerStub - a reference to the reudcer system
	 */
	public ReducerStub getReducer() {
		return this.reducer;
	}   // end WorkflowEngine.getReducer()

	/**
	 * This is a setter method to set the reducer attribute
	 * 
	 * @param red  - a reference to a reducer sysetm to use in setting 
	 * the reducer attribute
	 */
	public void setReducer(ReducerStub red) {
		this.reducer = reducer;
	}   // end WorkflowEngine.setReducer(ReducerStub reducer)

	/**
	 * This is a getter method to return the workflowRepo attribute
	 * 
	 * @return WorkflowRepo
	 */
	public WorkflowRepo getWorkflowRepo() {
		return workflowRepo;
	}

	/**
	 * This is a setter method to set the workflowRepo attribute
	 * 
	 * @param workflowRepo  - the WorkflowRepo used to set the workflowRepo attribute
	 * 
	 * @return void
	 */
	public void setWorkflowRepo(WorkflowRepo workflowRepo) {
		this.workflowRepo = workflowRepo;
	}

	/**
	 * This is a getter method to return the workflowDescriptionRepo attribute
	 * 
	 * @return WorkflowDescriptionRepo
	 */
	public WorkflowDescriptionRepo getWorkflowDescriptionRepo() {
		return workflowDescriptionRepo;
	}

	/**
	 * This is a setter method to set the workflowDescriptionRepo attribute
	 * 
	 * @param repo  - the WorkflowDescriptionRepo used to set the 
	 * workflowDescriptionRepo attribute
	 * @return void
	 */
	public void setWorkflowDescriptionRepo(WorkflowDescriptionRepo repo) {
		this.workflowDescriptionRepo = repo;
	}

	/**
	 * DEPRECATE!  NOT USED
	 * 
	 * This it the overridden method from the interface.  It is this method
	 * that is invoked by an external entity that wants the Workflow System to
	 * do something.
	 * 
	 * @param msg  - the incoming message that is passed to a workflow for processing
	 * 
	 * @return void
	 */
	public void handleMsg(Message msgIn) {
		logger.debug("WorkflowEngine.handleMsg()\n\tmsgIn: ", msgIn.toString());
		
		Message msgOut;
		DataSet dataSet = msgIn.getDataSetRef();
		MetaData metaData = dataSet.getMetaData();
		WorkflowDescription workflowDescription;
		
		/**
		 * Determine which WorkflowDescription the incoming msg is associated wtih.  
		 * 
		 */
		workflowDescription = workflowDescriptionRepo.findWorkflowDescription(metaData);
		// need to handle what happens if no workflow description is returned
		
		/**
		 * Then determine if the there is an existing Workflow that binds the 
		 * Data Set from the message with the WorkflowDescription 
		 */
		
		workflow = workflowRepo.findWorkflow(dataSet, workflowDescription);
		
		// Ask the workflow for the next outgoing message
		// A return of null indicates no follow on message
		msgOut = workflow.handleMsg(msgIn);
		
		if (msgOut != null) {
			reducer.processMessage(msgOut, this);
		}
		
	}

}   // end WorkflowEngine class 