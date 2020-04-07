package org.eclipse.ice.modeling.workflowDescription.action;

import org.eclipse.ice.modeling.workflow.Workflow;
import org.eclipse.ice.modeling.workflowEngine.Message;
import org.eclipse.ice.modeling.experiment.DataSet;

/**
 * The GroupStitchAction class holds the details of invoking a child workflow to reduce a specific sequence.
 * 
 * @author John Hetrick
 */
public class GroupStitchAction extends GroupAction {

	/**
	 * This is the constructor for the GroupStitchAction class
	 */
	public GroupStitchAction() {
		super();
		System.out.println("GroupStitchAction() constructor");
	}

	/**
	 * This is another constructor for the GroupStitchAction class that takes in
	 * an argument of enum that identifies the instruction this is
	 * @param instr - the InstructionGroup to us in initializing the class
	 */
	public GroupStitchAction(InstructionGroup instr) {
		super(instr);
		System.out.println("GroupStitchAction() constructor");
	}

	/**
	 * The execute method is responsible for performing the action when invoked.
	 */
	public Object execute() {
		// TODO - implement GroupStitchAction.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * This execute method takes a parameter and returns an object.
	 * @param obj - generic parameter used to pass in information needed to complete the action
	 */
	public Object execute(Object obj) {
		// TODO - implement GroupStitchAction.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is the primary method to make the action take action (execute).  The
	 * action is to have the a sequence stitched to the other sequences.  Currently this
	 * assumes a single sequence (recently reduced) is to be stitched to the others
	 * 
	 * NOTE: At this point I don't know how stitching of the workflows works so this is
	 * simply a dummy simulation of my own creation.
	 * 
	 * @param arg1 - this genric parametere can be used for anything but is currenlty not
	 *    used for this method in this class.
	 * @param arg2 - this is a parameter that can be used for anything, expected to be
	 *    the last msg received. It contains the DataSet for the Seq. Otherwise the
	 *    incoming msg is not relevant
	 */
	public Object execute(Object arg1, Object arg2) {
		System.out.println("GroupStitchAction.execute(Object arg1, Object arg2)");
		
		Message msgIn = (Message) arg2,
				msgOut = new Message ("STITCH");
		msgOut.setDataSetRef(msgIn.getDataSetRef());
		
		return msgOut;
		
	}

}