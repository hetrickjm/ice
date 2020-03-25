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
package org.eclipse.ice.modeling.workflowDescription;

import java.util.*;

import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflowDescription.tasks.*;

/**
 * THIS CLASS IS FOR THE EXPLORATION OF THE WORKFLOW CONCEPTS AND MAY BE CHANGED 
 * OR DEPRECATED IN THE FUTURE
 * 
 * The WorkflowDescriptionRepo class is the repository for all the WorkflowDescriptions 
 * the AR Workflow system is aware of.  This class holds the set of WorkflowDescriptions 
 * that are avaialble for use with a Workflow.  It also contains the mapping of what 
 * WorkflowDescriptions are associated with a specific DataSet.
 * 
 * NOTE: In the future this may be a facade to persistent storage for the contained 
 * WorkflowDescriptions
 * 
 * @author John Hetrick
 */
public class WorkflowDescriptionRepo {

	/**
	 * The workflowDescriptionSet is the repository for all WorkflowDescriptions.
	 * 
	 * NOTE: In the future this may be a facade to some persistent storage.  This is TBD based
	 * on any non-functional requirements
	 */
	private Map <String, WorkflowDescription> workflowDescriptionSet;
	
	/**
	 * The expRepo attribute is the repository that holds all the Experiments
	 */
	private ExperimentRepo expRepo;

	/**
	 * This is the constructor for the WorkflowDescriptionRepo class
	 */
	public WorkflowDescriptionRepo() {
	
	}

	/**
	 * THIS METHOD IS FOR EXPLORING THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method initializes and/or creates the workflowDescriptionSet.  This is
	 * the set of WorkflowDescriptions.
	 */
	private void initWorkflowDescriptionSet() {
		System.out.println("WorkflowDescriptionRepo() constructor");
		
		this.workflowDescriptionSet = new Hashtable<String, WorkflowDescription>();
		
		// Initialize the set of WorkflowDescriptions.  
		// FUTURE: This set should be create by reading from some persistent storage
		// e.g. a file or a database
		this.initWorkflowDescriptionSet();
		
		// Initialize the mapSet.  The mapping between DataSets and WorkflowDescriptions
		// FUTURE: This set should be create by reading from some persistent storage
		// e.g. a file or a database
		//this.initMapSet();
	}

	/**
	 * THIS METHOD IS FOR EXPLORING THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method creates a canned WorkflowDescription for a Sequence.
	 */
	private WorkflowDescription testCreateSeqWFD() {
		System.out.println("WorkflowDescriptionRepo.testCreateSeqWFD()");
		// Init some variables
		int index;
		List <Task> taskSet = new ArrayList <Task>();
		WorkflowDescription wfd = new WorkflowDescription("WDT-0", WorkflowDescriptionType.SEQ);
		
		System.out.println("\tCreating Seq Task 1");
		// Create the 3 MsgActions to be used in Tasks
		// These represent the 3 messages assoicated with getting a DataSet reduced
		// NOTE:  THIS IS ONLY FOR EXPLORATION
		Message msgCat   = new Message("CATALOG.DATA_READY");
		ActionMsg actCat = new ActionMsg(msgCat);
		taskSet.add(new Task("TSK-0", actCat));                // Add a new Task
		index = taskSet.size() - 1;
		
		// Create and set the completion criteria for this task
		Criteria startCrit1 = new StringCriteria("CATALOG.STARTED");
		Criteria cmpltCrit1 = new StringCriteria("CATALOG.COMPLETE");
		CompletionCriteria complete1 = new CompletionCriteria();
		complete1.addCompletionCreiteria(startCrit1);
		complete1.addCompletionCreiteria(cmpltCrit1);
		
		taskSet.get(index).setCompletionCriteria(complete1);        // Set the completion criteria
		wfd.addTask(taskSet.get(index));                            // Add the task to the workflowDescription
		
		/////////////////////////////////////////////////////////
		System.out.println("\tCreating Seq Task 2");
		// Add a second task
		Message msgRed   = new Message("REDUCTION.DATA_READY");
		ActionMsg actRed = new ActionMsg(msgRed);
		taskSet.add(new Task("TSK-1", actRed));
		index = taskSet.size() - 1;
		
		// Create and set the completion criteria for this task
		Criteria startCrit2 = new StringCriteria("REDUCTION.STARTED");
		Criteria cmpltCrit2 = new StringCriteria("REDUCTION.COMPLETE");
		CompletionCriteria complete2 = new CompletionCriteria();
		complete2.addCompletionCreiteria(startCrit2);
		complete2.addCompletionCreiteria(cmpltCrit2);
		
		taskSet.get(index).setCompletionCriteria(complete2);        // Set the completion criteria
		wfd.addTask(taskSet.get(index));                            // Add the task to the workflowDescription
		
		/////////////////////////////////////////////////////////
		System.out.println("\tCreating Seq Task 3");
		// Add a Third task
		Message msgRedCat   = new Message("REDUCTION_CATAGORY.DATA_READY");
		ActionMsg actRedCat = new ActionMsg(msgRedCat);
		taskSet.add(new Task("TSK-2", actRedCat));
		index = taskSet.size() - 1;
		
		// Create and set the completion criteria for this task
		Criteria startCrit3 = new StringCriteria("REDUCTION_CATAGORY.STARTED");
		Criteria cmpltCrit3 = new StringCriteria("REDUCTION_CATAGORY.COMPLETE");
		CompletionCriteria complete3 = new CompletionCriteria();
		complete3.addCompletionCreiteria(startCrit3);
		complete3.addCompletionCreiteria(cmpltCrit3);
		
		taskSet.get(index).setCompletionCriteria(complete3);        // Set the completion criteria
		wfd.addTask(taskSet.get(index));                            // Add the task to the workflowDescription
		
		// Add WorkflowDescription CompletionCriteria
		Criteria taskCrit = new StringCriteria("TASKS.COMPLETE");
		CompletionCriteria taskComplete = new CompletionCriteria();
		taskComplete.addCompletionCreiteria(taskCrit);
		wfd.setCompleteCriteria(taskComplete);
		
		return wfd;
	}

	/**
	 * THIS METHOD IS FOR EXPLORING THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method creates a canned WorkflowDescription for a Group.
	 */
	private WorkflowDescription testCreateGroupWFD() {
		System.out.println("WorkflowDescriptionRepo.testCreateGroupWFD()");
		// Init some variables
		int index;
		List <Task> taskSet = new ArrayList <Task>();
		
		// Need to add a workflowDescription CompletionCriteria
		WorkflowDescription wfd = new WorkflowDescription("WDG-0", WorkflowDescriptionType.GROUP);
		
		System.out.println("\tCreating Group Task 1");
		// Create the 3 MsgActions to be used in Tasks
		// These represent the 3 messages assoicated with getting a DataSet reduced
		// NOTE:  THIS IS ONLY FOR EXPLORATION
		ActionGroup actRed = new ActionGroup(InstructionGroup.REDUCE);
		taskSet.add(new Task("GTSK-0", actRed));                // Add a new Task
		index = taskSet.size() - 1;
		
		// Create and set the completion criteria for this task
		Criteria crit1 = new StringCriteria("CHILD.COMPLETE");
		CompletionCriteria complete1 = new CompletionCriteria();
		complete1.addCompletionCreiteria(crit1);
		
		taskSet.get(index).setCompletionCriteria(complete1);        // Set the completion criteria
		
		// Add the task to the workflowDescription
		wfd.addTask(taskSet.get(index));
	
		/////////////////////////////////////////////////////////
		System.out.println("\tCreating Group Task 2");
		// Add a second task
		ActionGroup actStitch = new ActionGroup(InstructionGroup.STITCH);
		taskSet.add(new Task("GTSK-1", actStitch));
		index = taskSet.size() - 1;
		
		// Create and set the completion criteria for this task
		Criteria crit2 = new StringCriteria("NONE");
		CompletionCriteria complete2 = new CompletionCriteria();
		complete2.addCompletionCreiteria(crit2);
		
		taskSet.get(index).setCompletionCriteria(complete2);        // Set the completion criteria
		wfd.addTask(taskSet.get(index));                            // Add the task to the workflowDescription
		/////////////////////////////////////////////////////////
		
		// Add WorkflowDescription CompletionCriteria
		Criteria groupCrit = new StringCriteria("REDUCE&STITCH");
		CompletionCriteria groupComplete = new CompletionCriteria();
		groupComplete.addCompletionCreiteria(groupCrit);
		wfd.setCompleteCriteria(groupComplete);
		
		return wfd;
	}

	/**
	 * THIS METHOD IS FOR EXPLORING THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the workflowDescSet attribute
	 * 
	 * NOTE: This is for testing only and should not be used.  This class is the repo
	 * @param metaData - the meta data that holds the key to identifying a specific WorkflowDescription
	 */
	public WorkflowDescription getWorkflowDescription(MetaData metaData) {
		System.out.println("WorkflowDescriptionRepo.getWorkflowDescription(MetaData metaData)");
		
		String key;   // This is to create the key to use to find the proper WorkflowDescription
		
		// Create the key from the MetaData
		key = metaData.getInstrumentID();
		key += "/" + metaData.getExperimentID();
		key += "/" + metaData.getGroupID();
		key += "/DT-" + metaData.getDataType();
		System.out.println("\tkey: " + key);
		
		WorkflowDescription wd = this.workflowDescriptionSet.get(key);
		
		return wd;
	}

	/**
	 * This is a setter method to add a WorkflowDescription to the workflowDescriptionSet attribute
	 * @param key - String used as a key to match to the WorkflowDescription
	 * @param description - description to add to the workflowDescriptionSet attribute
	 */
	public void addWorkflowDescription(String key, WorkflowDescription description) {
		this.workflowDescriptionSet.put(key, description);
	}

	/**
	 * This method searches for a WorkflowDescription in the mapSet based on the MetaData key.
	 * This method determines the key then searches for a pair.
	 * @param metaData - the MetaData from which to derive the meta data key
	 * @return WorkflowDescription
	 */
	public WorkflowDescription findWorkflowDescription(MetaData metaData) {
		System.out.println("WorkflowDescriptionRepo.getWorkflowDescription(MetaData metaData)");
		
		String key;   // This is to create the key to use to find the proper WorkflowDescription
		
		// Create the key from the MetaData.  The key is for the sequence.
		// However, the key applies to all sequence workflows in a particular group.  Hence
		// the sequence number is not part of the key
		key = metaData.getInstrumentID() +
			"/" + metaData.getExperimentID() +
			"/" + metaData.getGroupID() +
			"/DT-" + metaData.getDataType();
		System.out.println("\tkey: " + key);
		
		WorkflowDescription wd = this.workflowDescriptionSet.get(key);
		
		return wd;
	}

	/**
	 * This method searches for a WorkflowDescription in repo
	 * This method determines the key then searches for a pair.
	 * @param key - the to find the WorkflowDescription
	 * @return WorkflowDescription
	 */
	public WorkflowDescription findWorkflowDescription(String key) {
		System.out.println("WorkflowDescriptionRepo.getWorkflowDescription(MetaData metaData)");
		
		System.out.println("\tkey: " + key);
		
		WorkflowDescription wd = this.workflowDescriptionSet.get(key);
		
		return wd;
	}

	/**
	 * This is a getter method to return the expRepo (experiment repository) attribute
	 * @return ExperimentRepo
	 */
	public ExperimentRepo getExpRepo() {
		return this.expRepo;
	}

	/**
	 * This is a setter method to set the expRepo (experiment repository) attribute
	 * @param expRepo - the ExperimentRepo used to set the expRepo attribute
	 */
	public void setExpRepo(ExperimentRepo expRepo) {
		this.expRepo = expRepo;
	}

}