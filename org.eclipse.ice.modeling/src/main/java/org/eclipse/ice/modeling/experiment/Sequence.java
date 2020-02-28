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

import org.eclipse.ice.modeling.workflow.*;

/**
 * The Sequence Class represents a run or a set of data from an
 * experiment.  Sequences generally come in groups.  The sequence
 * contains one or more workflow if the associated data set has
 * been reduced.
 * 
 * NOTE: It is anticipated that this is a larger concept than simply
 * belongs to the Workflow System.  This concept is likely contains
 * other information for other purposes and resides in a persistent
 * storage mechanism along with the experiment it belongs to.  This
 * is outside the scope of this prototype.
 * 
 * @author John Hetrick
 */
public class Sequence {

	/**
	 * The seqeunceNum attribute is the sequence number of this data 
	 * set as part of a set of data sets (sequences or runs)
	 */
	private int sequenceNum;
	
	/**
	 * The workflows attribute holds the set of workflows executed 
	 * against the sequence.  There may be more than one if/when the 
	 * data set is reduced multiple times.
	 * 
	 * NOTE: This should be a list that can grow dynamically
	 */
	private Workflow[] workflowSet;

	/**
	 * This is the constructor for the Sequence class
	 */
	public Sequence() {
		System.out.println("Sequence() constructor");
	}

	/**
	 * This is a getter method to return the sequenceNum attribute which 
	 * is the number of this data set in the sequence it is a part of
	 * 
	 * @return int - the value of the sequenceNum attribute
	 */
	public int getSequenceNum() {
		System.out.println("Sequence.getSequenceNum()");
		return this.sequenceNum;
	}

	/**
	 * This is a setter method to set the sequenceNum attribute which is 
	 * the number of this data set in the sequence it is a part of
	 * 
	 * @param num  - num is used to the the sequenceNum attribute
	 */
	public void setSequenceNum(int num) {
		System.out.println("Sequence.setSequenceNum(int num)");
		this.sequenceNum = num;
	}

	/**
	 * This is a getter method to return the workflowSet attribute
	 * 
	 * @return Workflow[] - workflowSet attribute
	 */
	public Workflow[] getWorkflowSet() {
		System.out.println("Sequence.getWorkflowSet()");
		return this.workflowSet;
	}

	/**
	 * This is a setter method to add a new workflow to the workflowSet attribute
	 * 
	 * @param workflow  - the workflow to be added to the workflowSet attribute
	 */
	public void addWorkflow(Workflow workflow) {
		System.out.println("Sequence.addWorkflow(Workflow workflow)");
		this.workflowSet[0] = workflow;
	}

	/**
	 * This is another constructor for the Sequence class that takes in the SequenceNum as a parameter
	 * @param num  - the number of the sequence in a group
	 */
	public Sequence(int num) {
		// TODO - implement Sequence.Sequence
		throw new UnsupportedOperationException();
	}

	/**
	 * This method is to find and return the latest Workflow associated with the Sequence
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow() {
		// TODO - implement Sequence.findWorkflow
		throw new UnsupportedOperationException();
	}

}   // end class Sequence