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

import java.util.Hashtable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflowDescription.*;

/**
 * THIS CLASS IS FOR THE EXPLORATION OF THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED IN THE FUTURE
 * 
 * WorkflowRepo is the repository that holds all the Workflows.
 * 
 * NOTE: The current thinking is that a workflow should be found by finding the associated Experiment then looking to see if the appropriate experiment part (group or run) has the appropriate workflow.  It may be expedient to hold (cache) a set of "recently" used workflows as well if speed is important.
 * 
 * @author John Hetrick
 */
public class WorkflowRepo {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(Workflow.class);
	
	/**
	 * This attribute holds the set of workflow instances.  
	 * (java.util.Map<String, AR-Workflow_Model.org.eclipse.ice.modeling.workflow.Workflow>)
	 */
	private Map <String, Workflow> workflowSet;

	/**
	 * The expRepo attribute is the repository that holds all the Experiments.
	 */
	private ExperimentRepo expRepo;

	/**
	 * The workflowDescriptionRepo attribute is the repository that holds all the
	 * WorkflowDescriptions
	 */
	private WorkflowDescriptionRepo workflowDescriptionRepo;

	/**
	 * This is the constructor for the WorkflowRepo class
	 */
	public WorkflowRepo() {
		logger.debug("WorkflowRepo() constructor");
		
		// Initialize the workflow repository attribute
		this.workflowSet = new Hashtable<String, Workflow>();
	}

	/**
	 * This is a getter method to return a workflow from the workflow set.
	 */
	public Workflow getWorkflowSet(String key) {
		logger.debug("WorkflowRepo.getWorkflowSet(String key) ");
		
		return this.workflowSet.get(key);
	}

	/**
	 * This is a setter method to add a workflow to the workflow set.
	 * @param workflow  - the workflow to add to the workflowSet attribute
	 */
	public void addWorkflowSet(String key, Workflow workflow) {
		System.out.println("WorkflowRepo.setWorkflowSet(String key, Workflow workflow)");
		
		this.workflowSet.put(key, workflow);
	}

	/**
	 * This is a getter method to return the expRepo (experiment repository) attribute
	 * 
	 * @return ExperimentRepo
	 */
	public ExperimentRepo getExpRepo() {
		System.out.println("WorkflowRepo.getExpRepo()");
		
		return this.expRepo;
	}

	/**
	 * This is a setter method to set the expRepo (experiment repository) attribute
	 * 
	 * @param expRepo  - the ExperimentRepo used to set the expRepo attribute
	 * @return void
	 */
	public void setExpRepo(ExperimentRepo expRepo) {
		System.out.println("WorkflowRepo.setExpRepo(ExperimentRepo expRepo)");
		this.expRepo = expRepo;
	}

	/**
	 * This is a getter method to return the workflowDescription attribute
	 * 
	 * @return the workflowDescriptionRepo
	 */
	public WorkflowDescriptionRepo getWorkflowDescriptionRepo() {
		return workflowDescriptionRepo;
	}

	/**
	 * This is a setter method to set the workflowDescription attribute.
	 * 
	 * @param workflowDescriptionRepo the workflowDescriptionRepo to set
	 */
	public void setWorkflowDescriptionRepo(WorkflowDescriptionRepo repo) {
		this.workflowDescriptionRepo = repo;
	}

	/**
	 * The findWorkflow method finds a Workflow that governs the processing of a Data Set.
	 * If the Workflow does not exist, one has to be created
	 * 
	 * NOTE: Current thinking (logic) is to find the Experiment associated with the dataSet.
	 * Then get the workflow from the Group of the workflow and invoke it.  If an Experiment
	 * does not exist one gets created.  If no workflow exists it gets created and associated
	 * with the Experiment Group and Sequences if necessary
	 * 
	 * @param dataSet - the Data Set that holds the meta data used to help identify a workflow
	 * @param wd - the WorkflowDescriptor use to help identify the workflow
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow(DataSet dataSet, WorkflowDescription wd) {
		logger.debug("WorkflowRepo.findWorkflow(DataSet dataSet, WorkflowDescription wd) ");
		
		// Initi local vars
		Sequence   seq      = null;
		Group      group    = null;
		Experiment exp      = null;
		Workflow   workflow = null;
		
		// Create the Experiment key (instrumentID + experimentID from the Data Set
		String key = dataSet.getMetaData().getInstrumentID() + 
				"/" + dataSet.getMetaData().getExperimentID();
		
		// Get the experiment from the repo
		exp = this.expRepo.findExperiment(key);
		if (exp == null) {
			exp = this.expRepo.createExperiment(dataSet);
		}
		
		// Get the group workflow by getting the group from the Experiment.
		// NOTE: Should check if group is null, but for exploration this is fine 
		group = exp.findGroup(dataSet.getMetaData().getGroupID());
		workflow = group.findWorkflow();
		
		// If a workflow cannot be found create the required workflows:
		//    - GroupWF
		//    - RunWF (is set as a childWorkflow for the group
		if (workflow == null) {
			workflow = this.createWorkflow(dataSet, wd, group);
		}
		
		// Check there is a child workflow that matches the sequence.  If
		// not then create one
		
		// Return the Group workflow
		return workflow;
	}

	/**
	 * This method creates a new workfow for a group.  A new group workflow also means 
	 * a new RunWF.  This is because if a group Workflow does not exist then the RunWF 
	 * will not exist either.
	 * 
	 * This method takes a Group key taken from the Metadata and finds the appropriate 
	 * WorkflowDescription to add to the newly created Workflow.
	 * 
	 * NOTE: There should be a factory to create Workflows
	 * @param dataSet - the DataSet used to help create workflows
	 * @param description - the WorkflowDescription to help create workflows
	 * @param group - group to create a workflow for
	 * 
	 * @return Workflow
	 */
	private Workflow createWorkflow(DataSet dataSet, WorkflowDescription description, Group group) {
		logger.debug("WorkflowRepo.createWorkflow(DataSet dataSet, WorkflowDescription description, Group group) ");
		
		MetaData meta = dataSet.getMetaData();
		
		// CURRENLTY: assuming the passed in WorkflowDescription is for the sequence
		// Get the Group WorkflowDescription
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID();
		WorkflowDescription wfdg = this.workflowDescriptionRepo.findWorkflowDescription(key);
		// Need to handle the case if a WorkflowDescription for the group is not found
		
		/**
		 * Determine the number of workflows currently in the group.
		 * Then create an ID for the group Workflow based on:
		 *     - instrumentID
		 *     - experimentID
		 *     - groupID
		 *     - workflow number ("WFG-#)
		 */
		int wfgCount = group.workflowCount();
		String gkey = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/WFG-" + wfgCount;
		GroupWF groupWF = new GroupWF(gkey, dataSet, wfdg);
		
		// Since a group workflow was not found the both a sequence (run) workflow and 
		// the group workflow must be created.
		/**
		 * Determine the number of workflows currently in the group.
		 * Then create an ID for the group Workflow based on:
		 *     - instrumentID
		 *     - experimentID
		 *     - groupID
		 *     - workflow number ("WFS-#)
		 */
		int wfsCount = group.findSequence(0).workflowCount();
		String skey = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/WFS-" + wfsCount;
		SeqWF seqWF = new SeqWF(skey, dataSet, description);
		
		// Add the Sequence (run) Workflow to the sequence
		Sequence seq = group.findSequence(meta.getSequenceNumber());
		seq.addWorkflow(seqWF);
		
		// Add the sequence workflow as a child to the group workflow
		groupWF.addChildWorkflow(seqWF);
		
		// Add the group Workflow to the group
		group.addWorkflow(groupWF);
		
		// Add the workflows to the repo for easier retrieval
		// NOTE: Need to decide if only group workflows or all workflow should go
		// in the repo.  Only the group workflows should be engaged so the group
		// can then manage the sequences which are child workflows
		// use the same keys from above
		this.workflowSet.put(gkey, groupWF);
		this.workflowSet.put(skey, seqWF);
		
		return groupWF;
	}

}   // end class WorkflowRepo