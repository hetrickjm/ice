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
	 * This attribute holds the set of workflow instances
	 */
	private Workflow[] workflowSet;
	/**
	 * The expRepo attribute is the repository that holds all the Experiments.
	 */
	private ExperimentRepo expRepo;

	/**
	 * This is the constructor for the WorkflowRepo class
	 */
	public WorkflowRepo() {
		// TODO - implement WorkflowRepo.WorkflowRepo
		throw new UnsupportedOperationException();
	}

	/**
	 * This is a getter method to return a workflow from the workflow set.
	 */
	public Workflow getWorkflowSet() {
		return this.workflowSet[0];
	}

	/**
	 * This is a setter method to add a workflow to the workflow set.
	 * @param workflow  - the workflow to add to the workflowSet attribute
	 */
	public void setWorkflowSet(Workflow workflow) {
		this.workflowSet[0] = workflow;
	}

	/**
	 * This is a getter method to return the expRepo (experiment repository) attribute
	 * 
	 * @return ExperimentRepo
	 */
	public ExperimentRepo getExpRepo() {
		return this.expRepo;
	}

	/**
	 * This is a setter method to set the expRepo (experiment repository) attribute
	 * 
	 * @param expRepo  - the ExperimentRepo used to set the expRepo attribute
	 * @return void
	 */
	public void setExpRepo(ExperimentRepo expRepo) {
		this.expRepo = expRepo;
	}

	/**
	 * The findWorkflow method finds a Workflow that governs the processing of a Data Set.
	 * If the Workflow does not exist, one has to be created
	 * 
	 * NOTE: This may be better handled by a class that represents a repository of workflows
	 * but that has not yet come into existence.
	 * 
	 * @param dataSet - the Data Set that holds the meta data used to help identify a workflow
	 * @param wd - the WorkflowDescriptor use to help identify the workflow
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow(DataSet dataSet, WorkflowDescription wd) {
		System.out.println("WorkflowEngine.findWorkflow(DataSet dataSet, WorkflowDescription wd)");
		
		Sequence   seq      = null;
		Group      group    = null;
		Experiment exp      = null;
		Workflow   workflow = null;
		
		// Get the experiment ID from the Data Set
		String expID = dataSet.getMetaData().getExperimentID();
		
		// Get the experiment from the repo
		exp = expRepo.getExperiment(expID);
		
		// FUTURE: THIS SHOULD CALL A FACTORY TO CREATE A NEW EXPERIMENT &
		// ASSOCIATED WORKFLOWS
		// Create an experiment along with the associated group and sequence
		if (exp == null) {
			System.out.println("Need to create a new Experiment");
			
			seq   = new Sequence(dataSet);   // This is the first Sequence (run) in a Group
			group = new Group(dataSet, seq);
			exp   = new Experiment(dataSet.getMetaData().getExperimentID(), group);
		}
		
		// Get the group workflow by getting the group from the Experiment.
		group = exp.findGroup(dataSet.getMetaData().getGroupID());
		workflow = group.findWorkflow();
		
		// If a workflow cannot be found create the required workflows
		if (workflow == null) {
			workflow = this.createWorkflow(dataSet, wd, group, seq);
		}
		
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
	 * @param seq - sequence to create a workflow for
	 * 
	 * @return Workflow
	 */
	private Workflow createWorkflow(DataSet dataSet, WorkflowDescription description, Group group, Sequence seq) {
		System.out.println("WorkflowRepo.createWorkflow()");
		
		// if a group workflow was not found the both a sequence (run) workflow and 
		// the group workflow must be created.
		RunWF   seqWF   = new RunWF(dataSet, description);
		GroupWF groupWF = new GroupWF(dataSet, description);
		
		// Add the Sequence (run) Workflow to the sequence
		seq.addWorkflow(seqWF);
		
		// Add the sequence workflow as a child to the group workflow
		groupWF.addChildWF(seqWF);
		
		
		// Add the group Workflow to the group
		group.addWorkflow(groupWF);
		
		return group.findWorkflow();   // CAUTION: this method returns the first workflow
	}

}   // end class WorkflowRepo