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
	 * The dataSet attribute is the DataSet associated with the Sequence.
	 */
	private DataSet dataSet;
	
	/**
	 * The workflows attribute holds the set of workflows executed 
	 * against the sequence.  There may be more than one if/when the 
	 * data set is reduced multiple times.
	 * 
	 * NOTE: This should be a list that can grow dynamically
	 */
	private Workflow[] workflowSet = new Workflow[2];

	/**
	 * The workflowIndex is set to the array index of the latest workflow.  -1
	 * means the array is empty and there are no workflows
	 * 
	 * NOTE: This will likely be DEPRECATED when implementing a dynamic
	 * workflow list.
	 */
	private int workflowIndex = -1;

	/**
	 * This is the constructor for the Sequence class
	 */
	public Sequence() {
		System.out.println("Sequence() constructor");
	}

	/**
	 * This is another constructor for the Sequence class that takes in 
	 * the SequenceNum as a parameter
	 * 
	 * @param set  - the DataSet to associate with the Sequence
	 */
	public Sequence(DataSet set) {
		System.out.println("Sequence(DataSet set) constructor");
		
		this.dataSet = set;
		this.sequenceNum = set.getMetaData().getSequenceNumber();
		
		this.workflowSet[0] = null;
		this.workflowSet[1] = null;
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
	 * NOTE: This should be DEPRECATED.  The sequenceNum should only be
	 * set from the DataSet
	 * 
	 * @param num - num is used to the the sequenceNum attribute
	 */
	public void setSequenceNum(int num) {
		System.out.println("Sequence.setSequenceNum(int num)");
		this.sequenceNum = num;
	}

	/**
	 * This is a getter method to return the dataSet attribute
	 * 
	 * @return the dataSet
	 */
	public DataSet getDataSet() {
		return this.dataSet;
	}

	/**
	 * This is a setter method to set the dataSet attribute
	 * 
	 * @param set the DataSet to set the dataSet attribute
	 * 
	 * @return void
	 */
	public void setDataSet(DataSet set) {
		this.dataSet = set;
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
	 * 
	 * @return void
	 */
	public void addWorkflow(Workflow workflow) {
		System.out.println("Sequence.addWorkflow(Workflow workflow)");
		
		// If the index is any negative number it means there are no sequences
		if (this.workflowIndex < 0) {
			this.workflowSet[0] = workflow;
		}
		else if (this.workflowIndex < 1) {
			this.workflowSet[this.workflowIndex] = workflow;
			this.workflowIndex++;
		}
		// else do nothing.  Need to think about how to throw an error
		// however this is a temporary implementation for the Prototype
	}

	/**
	 * This method is to find and return the latest Workflow associated with the Sequence
	 * 
	 * @param id - the id used to find the desired workflow
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow(String id) {
		System.out.println("Sequence.findWorkflow()");
		
		// THIS WILL CHANGE when workflows are created using a dynamic list
		
		boolean found = false;
		int i = 0;
		
		for (i = 0; id != this.workflowSet[i].getWfID() && i <= this.workflowIndex; i++) {
			if (id == this.workflowSet[i].getWfID()) {
					found = true;
			}
		}
		
		if (found)
			return this.workflowSet[i];
		else
			return null;
	}

}   // end class Sequence