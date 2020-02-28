package org.eclipse.ice.modeling.workflowDescription;

import org.eclipse.ice.modeling.workflowEngine.*;
import org.eclipse.ice.modeling.experiment.*;

/**
 * THIS CLASS IS FOR THE EXPLORATION OF THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED IN THE FUTURE
 * 
 * The WorkflowDescriptionRepo is the repository for all the WorkflowDescriptions the AR Workflow system is aware of.
 * 
 * NOTE: In the future this may be a facade to persistent storage for the contained WorkflowDescriptions
 * 
 * @author John Hetrick
 */
public class WorkflowDescriptonRepo {

	/**
	 * The workflowDescriptionSet is the repository for all WorkflowDescriptions.
	 * 
	 * NOTE: In the future this may be a facade to some persistent storage.  This is TBD based
	 * on any non-functional requirements
	 */
	private WorkflowDescription workflowDescriptionSet;
	/**
	 * The mapSet attribute holds the set of metaData key/workflowDescription pairs
	 */
	private Mapper mapSet;
	/**
	 * The expRepo attribute is the repository that holds all the Experiments
	 */
	private ExperimentRepo expRepo;

	/**
	 * THIS METHOD IS FOR EXPLORING THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the workflowDescSet attribute
	 * 
	 * NOTE: This is for testing only and should not be used.  This class is the repo
	 * @param metaData  - the meta data that holds the key to identifying a specific WorkflowDescription
	 */
	public WorkflowDescription getWorkflowDescription(MetaData metaData) {
		// TODO - implement WorkflowDescriptonRepo.getWorkflowDescription
		throw new UnsupportedOperationException();
	}

	/**
	 * This is a setter method to add a WorkflowDescription to the workflowDescriptionSet attribute
	 * @param description  - description to add to the workflowDescriptionSet attribute
	 */
	public void addWorkflowDescription(WorkflowDescription description) {
		// TODO - implement WorkflowDescriptonRepo.addWorkflowDescription
		throw new UnsupportedOperationException();
	}

	/**
	 * This is a getter method to return the mapSet Attribute
	 * 
	 * NOTE: This is likely to be replaced or deprecated as it is unlikely that the whole map set will be used by any other entity
	 */
	public Mapper getMapSet() {
		return this.mapSet;
	}

	/**
	 * This is a setter method to add a Mapper to the mapSet attribute.  A Mapper is a pairing of a MetaData Key to a WorkDescription
	 * @param mapSet
	 */
	public void addMap(Mapper mapSet) {
		// TODO - implement WorkflowDescriptonRepo.addMap
		throw new UnsupportedOperationException();
	}

	/**
	 * This method searches for a WorkflowDescription in the mapSet based on the MetaData key.  This method determines the key then searches for a pair.
	 * 
	 * @return WorkflowDescription
	 * @param metaData  - the MetaData from which to derive the meta data key
	 */
	public WorkflowDescription findWorkflowDescription(MetaData metaData) {
		// TODO - implement WorkflowDescriptonRepo.findWorkflowDescription
		throw new UnsupportedOperationException();
	}

	/**
	 * THIS METHOD IS FOR EXPLORING THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the workflowDescSet attribute
	 * 
	 * NOTE: This is for testing only and should not be used.  This class is the repo
	 * @param metaData  - the meta data that holds the key to identifying a specific WorkflowDescription
	 */
	public WorkflowDescription getWorkflowDescription(MetaData metaData) {
		// TODO - implement WorkflowDescriptonRepo.getWorkflowDescription
		throw new UnsupportedOperationException();
	}

	/**
	 * This is a setter method to add a WorkflowDescription to the workflowDescriptionSet attribute
	 * @param description  - description to add to the workflowDescriptionSet attribute
	 */
	public void addWorkflowDescription(WorkflowDescription description) {
		// TODO - implement WorkflowDescriptonRepo.addWorkflowDescription
		throw new UnsupportedOperationException();
	}

	/**
	 * This method searches for a WorkflowDescription in the mapSet based on the MetaData key.  This method determines the key then searches for a pair.
	 * 
	 * @return WorkflowDescription
	 * @param metaData  - the MetaData from which to derive the meta data key
	 */
	public WorkflowDescription findWorkflowDescription(MetaData metaData) {
		// TODO - implement WorkflowDescriptonRepo.findWorkflowDescription
		throw new UnsupportedOperationException();
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
	 * @return void
	 * @param expRepo  - the ExperimentRepo used to set the expRepo attribute
	 */
	public void setExpRepo(ExperimentRepo expRepo) {
		this.expRepo = expRepo;
	}

}