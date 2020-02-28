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
	 * THIS ATTRIBUTE IS PRIMARILY FOR CONCEPT EXPLORATION.
	 * 
	 * This attribute is a set of mappings that pair Meta Data with WorkflowDescriptions.
	 * For now this is used to determine which Workflow Description is to be used on a
	 * particular Data Set
	 */
	private Mapper[] workflowMap;
	
	/**
	 * The workflowRepo attribute is the repository for all workflows
	 */
	private WorkflowRepo workflowRepo;
	
	/**
	 * The workflowDescriptionRepo attribute is the repository for 
	 * the set of WorkflowDescriptions
	 */
	private WorkflowDescriptonRepo workflowDescriptionRepo;

	/**
	 * This is the constructor for the WorkflowEngine class
	 */
	public WorkflowEngine() {
		System.out.println("WorkflowEngine() constructor");
		
		this.reducer = null;
		this.workflow = null;
		this.workflowMap = new Mapper[3];
		
		int len = workflowMap.length;
		
	}   // end WorkflowEngine() constructor

	/**
	 * THIS CONSTRUCTOR IS PRIMARILY FOR TESTING CONCEPTS AND MAY BE DEPRECATED
	 * 
	 * This is another constructor for the WorkflowEngine class that takes a 
	 * reference to a reducer and a workflow
	 * 
	 * @param reducer  - Reference to a reducer system
	 * @param workflow A workflow the workflow engine will work with
	 */
	public WorkflowEngine(ReducerStub reducer, Workflow workflow) {
		System.out.println("WorkflowEngine(ReducerStub reducer, Workflow workflow) constructor");
		
		this.reducer  = reducer;
		this.workflow = workflow;
		
	}   // end WorkflowEngine() constructor

	/**
	 * This it the overridden method from the interface.  It is this method
	 * that is invoked by an external entity that wants the Workflow System to
	 * do something.
	 * 
	 * @param msg  - the incoming message that is passed to a workflow for processing
	 * 
	 * @return void
	 */
	public void handleMsg(Message msg) {
		System.out.println("WorkflowEngine.handleMsg()");
		
		Message msgOut;
		DataSet dataSet = msg.getDataSetRef();
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
		msgOut = workflow.handleMsg(msg);
		
		if (msgOut != null) {
			reducer.processMessage(msgOut, this);
		}
		
	}   
	
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
	 * THIS IS A TEST METHOD TO EXPLORE CONCEPTS
	 * 
	 * The determineWorkflow method looks up which WorkflowDescription is to be
	 * use when processing the data set associated with the Meta Data that was
	 * passed in as a parameter.
	 * 
	 * Basic algorithm: Check in hierarchical order the following:
	 * - instrumentID, experimentID, groutID, dataType
	 * - instrumentID, experimentID, groutID
	 * - instrumentID, experimentID
	 * - instrumentID  (default)
	 * 
	 * @param metaData - the meta data from a data set that is used to determine 
	 * the workflowDescription that should used on the data set
	 * 
	 * @param dataSet  - the Data Set that holds the meta data used to determine 
	 * the Workflow
	 */
	public Workflow determineWorkflow(DataSet dataSet) {
		System.out.println("WorkflowEngine.determineWorkflow(DataSet dataSet)");
		
		WorkflowDescription wd = this.findWorkflowDescription(dataSet.getMetaData());
		// Now that we have the workflow description we need to see if there is
		// a workflow for the data set that has this description
		if ( wd != null ) {
			System.out.println("not sure what to do now");
			// Workflow findWorkflow( DataSet, WorkflowDescription)
		}
		
		return this.workflow;
	}

	/**
	 * This method finds a workflow that matches the key derived from the MetaData
	 * 
	 * @param metaData  - the meta data to determine the key for finding an 
	 * associated WorkflowDescription
	 */
	private WorkflowDescription findWorkflowDescription(MetaData metaData) {
		System.out.println("WorkflowEngine.findWorkflowDescription(MetaData metaData)");
		
		// Some local vars:
		WorkflowDescription wd = null;
		String key = "";
		boolean found = false;
		int i = 0, n = 0;
		int maxKeyPermutations = 4;   // need to make this a static value for the class
		
		// Loop through the set of meta data / WorkflowDescription pairs
		// to match the meta data ID
		// Workflow Description findWorkflowDescription( metaData)
		for (n = 1; n <= maxKeyPermutations;) {
			switch (n) {
				case 1:   // primary key
					key = metaData.getInstrumentID() +
						  "/" + metaData.getExperimentID() +
						  "/" + metaData.getGroupID() +
						  "/" + Integer.toString( metaData.getDataType() );
					break;
					
				case 2:   // secondary key
					key = metaData.getInstrumentID() +
						  "/" + metaData.getExperimentID() +
						  "/" + metaData.getGroupID();
					break;
					
				case 3:   // tertiary key
					key = metaData.getInstrumentID() +
						  "/" + metaData.getExperimentID();
					break;
					
				case 4:   // default key
					key = metaData.getInstrumentID();
					break;
			}   // end switch
			
			for (i = 0; (i < (workflowMap.length - 1)) && !found; i++) {
				if ( key == workflowMap[i].getMetaDataKey()) {
					wd = workflowMap[i].getWorkflowDescription();
					found = true;
				}
			}   // end for loop(i)
		}   // end for loop(n)
		
		return wd;
	}

	/**
	 * The findWorkflow method finds a Workflow that governs the processing of 
	 * a Data Set.  If the Workflow does not exist, one has to be created
	 * 
	 * NOTE: This may be better handled by a class that represents a repository 
	 * of workflows but that has not yet come into existence.
	 * 
	 * @param dataSet  - the Data Set that holds the meta data used to help 
	 * identify a workflow
	 * 
	 * @param wd  - the WorkflowDescriptor use to help identify the workflow
	 */
	private Workflow findWorkflow(DataSet dataSet, WorkflowDescription wd) {
		System.out.println("WorkflowEngine.findWorkflowDescription(MetaData metaData)");
		
		Workflow wf = new Workflow();
		return wf;
	}

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
	public WorkflowDescriptonRepo getWorkflowDescriptionRepo() {
		return workflowDescriptionRepo;
	}

	/**
	 * This is a setter method to set the workflowDescriptionRepo attribute
	 * 
	 * @param repo  - the WorkflowDescriptionRepo used to set the 
	 * workflowDescriptionRepo attribute
	 * @return void
	 */
	public void setWorkflowDescriptionRepo(WorkflowDescriptonRepo repo) {
		this.workflowDescriptionRepo = repo;
	}

}   // end WorkflowEngine class 