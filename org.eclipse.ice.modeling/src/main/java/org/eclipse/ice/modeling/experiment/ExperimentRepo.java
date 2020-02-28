package org.eclipse.ice.modeling.experiment;

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
	private Experiment[] experimentSet;

	/**
	 * This method returns an Experiment if it exists in the repository
	 * 
	 * @return Experiment
	 * @param expID  - the experiment ID to use in finding the Experiment in the repo
	 */
	public Experiment getExperiment(String expID) {
		// TODO - implement ExperimentRepo.getExperiment
		throw new UnsupportedOperationException();
	}

	/**
	 * This method adds an Experiment to the repository
	 * 
	 * @return void
	 * @param exp  - the Experiment to add to the repo
	 */
	public void addExperiment(Experiment exp) {
		// TODO - implement ExperimentRepo.addExperiment
		throw new UnsupportedOperationException();
	}

}