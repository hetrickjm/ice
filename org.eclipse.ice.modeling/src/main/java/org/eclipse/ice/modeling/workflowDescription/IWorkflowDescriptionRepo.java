package org.eclipse.ice.modeling.workflowDescription;

import org.eclipse.ice.modeling.experiment.*;

/**
 * THIS INTERFACE CLASS IS FOR THE EXPLORATION OF THE WORKFLOW CONCEPTS AND MAY BE CHANGED
 * OR DEPRECATED IN THE FUTURE
 * 
 * The IWorkflowDescriptionRepo class is the public interface for the repository for all the
 * WorkflowDescriptions the AR Workflow system is aware of.
 * 
 * NOTE: In the future this may be a facade to persistent storage for the contained
 * WorkflowDescriptions
 * 
 * @author John Hetrick
 */
public interface IWorkflowDescriptionRepo {

	/**
	 * This method searches for a WorkflowDescription in the mapSet based on the MetaData key.
	 * This method determines the key then searches for a pair.
	 * 
	 * FUTUER: method looks up which WorkflowDescription is to be
	 * use when processing the data set associated with the Meta Data that was
	 * passed in as a parameter.
	 * 
	 * Basic algorithm: Check in hierarchical order the following:
	 * - instrumentID, experimentID, groutID, dataType
	 * - instrumentID, experimentID, groutID
	 * - instrumentID, experimentID
	 * - instrumentID  (default)
	 * @param metaData - the MetaData from which to derive the meta data key
	 */
	IWorkflowDescription findWorkflowDescription(MetaData metaData);

	/**
	 * This method searches for a WorkflowDescription in the mapSet based on the MetaData key.
	 * This method determines the key then searches for a pair.
	 * 
	 * FUTUER: method looks up which WorkflowDescription is to be
	 * use when processing the data set associated with the Meta Data that was
	 * passed in as a parameter.
	 * 
	 * Basic algorithm: Check in hierarchical order the following:
	 * - instrumentID, experimentID, groutID, dataType
	 * - instrumentID, experimentID, groutID
	 * - instrumentID, experimentID
	 * - instrumentID  (default)
	 * @param key - the String from which to use to look up the WorkflowDescription
	 */
	IWorkflowDescription findWorkflowDescription(String key);

}