package org.eclipse.ice.modeling.workflow;

import org.eclipse.ice.modeling.workflowEngine.*;

/**
 * THIS INTERFACE CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * IWorkflow interface class provides access to the behavior of the Workflow component
 * 
 * @author John Hetrick
 */
public interface IWorkflow {

	/**
	 * This method is invoked by the workflow engine to have the workflow process an
	 * incoming message or action
	 * 
	 * This is the method from should be is overridden.
	 * @param msgIn - msgIn is the incoming message to be recognized and then to take action on
	 */
	Message handleMsg(Message msgIn);

	/**
	 * NEEDS TO BE MODIFIED FOR NEW STATUS MECHANISM
	 * 
	 * This method evaluates if the workflow is complete or not.  It returns true if it is complete
	 * or false if not complete.
	 * 
	 * Note: Need to figure out how to handle the ERROR state.
	 */
	boolean isComplete();

}