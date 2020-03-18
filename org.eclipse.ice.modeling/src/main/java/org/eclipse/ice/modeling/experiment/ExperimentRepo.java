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
package org.eclipse.ice.modeling.experiment;

import java.util.*;

import org.eclipse.ice.modeling.workflow.Workflow;

/**
 * THIS CLASS IS FOR THE EXPLORATION OF THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED IN THE FUTURE
 * 
 * The ExperimentRepo is the repository for all the experiments the AR workflow system is aware of.
 * 
 * NOTE: In the future this may be a facade to persistent storage for the contained Experiments
 * 
 * @author John Hetrick
 */
public class ExperimentRepo {

	/**
	 * The experimentSet attribute is the test of Experiments in the repository
	 */
	private Map <String, Experiment> experimentSet;

	/**
	 * This is the constructor for the ExperimentRepo class
	 */
	public ExperimentRepo() {
		System.out.println("ExperimentRepo() constructor");
		
		this.experimentSet = new Hashtable <String, Experiment>();
		
	}

	/**
	 * This method returns an Experiment if it exists in the repository
	 * 
	 * @param key - the key to use in finding the Experiment in the repo.  The key
	 * is the instrument ID + "/" + experiment ID
	 * 
	 * @return Experiment
	 */
	public Experiment findExperiment(String key) {
		System.out.println("ExperimentRepo.findExperiment(String key)");
		
		Experiment exp = null;
		
		// Check if the requested experiment exists
		if (this.experimentSet.containsKey(key)) {
			exp = this.experimentSet.get(key);
		}
		else {
			// Create a new experiment with all it parts from the metaData
		}
		
		return exp;
	}

	/**
	 * This method adds an Experiment to the repository
	 * 
	 * @param exp  - the Experiment to add to the repo
	 * @return void
	 */
	public void addExperiment(Experiment exp) {
		System.out.println("ExperimentRepo.addExperiment(Experiment exp)");

		// Create the key for the table.  The key is a concat of
		// the instrument ID and the experiment ID
		String key = exp.getInstrumentID() + "/" + exp.getExpID();
		
		this.experimentSet.put(key, exp);
	}

	/**
	 * The createExperiment method creates and Experiment based on the passed in DataSet
	 * which includes the MetaData for the experiment.  The Experiment is added to the
	 * repository and returned to caller.
	 * @param dataSet - the DataSet that contains the information for creating the Experiment
	 */
	public Experiment createExperiment(DataSet dataSet) {
		System.out.println("ExperimentRepo.createExperiment(DataSet dataSet)");
		
		MetaData meta = dataSet.getMetaData();  // Easy access to the MetaData
		
		/**
		 *  Create the parts of the experiment & the experiment:
		 *      - Sequence
		 *      - Group
		 *      - Experiment
		 */
		Sequence seq = new Sequence(dataSet);
		Group group = new Group(dataSet, seq);
		Experiment exp = new Experiment(meta.getExperimentID(), meta.getInstrumentID(), group);
		
		// Add the experiment to the repo
		this.addExperiment(exp);
		
		return exp;
		
	}

}