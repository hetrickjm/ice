package org.eclipse.ice.modeling.workflow;

import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflowDescription.*;

/**
 * THIS INTERFACE CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * IWorkflowRepo interface class exposes the workflow repository capabilities of the Workflow component
 * 
 * @author John Hetrick
 */
public interface IWorkflowRepo {

	/**
	 * 
	 * @param dataSet
	 * @param wd
	 */
	Workflow findWorkflow(DataSet dataSet, WorkflowDescription wd);

}