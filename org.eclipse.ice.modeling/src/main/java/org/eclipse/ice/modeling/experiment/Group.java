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
 * The Group is the Class that holds the set of sequences (runs).  The
 * group can have one or more workflows associated with it.
 * 
 * NOTE: It is anticipated that this is a larger concept than simply
 * belongs to the Workflow System.  This concept is likely contains
 * other information for other purposes and resides in a persistent
 * storage mechanism along with the experiment it belongs to.  This
 * is outside the scope of this prototype.
 * 
 * @author John Hetrick
 */
public class Group {

	/**
	 * The sequenceID attribute is the ID for the sequence set (or 
	 * group ID or run set ID).  This is the same as the sequenceID 
	 * on the first data set for a particular run set.
	 */
	private String sequenceID;
	
	/**
	 * The dataSet attribute is the DataSet associated with the Group.
	 * 
	 * NOTE: Need to think about this.  It may be the Group does not have
	 * to directly have the DataSet since each sequence will.
	 */
	private DataSet dataSet;
	
	/**
	 * The seqSet attribute holds the set of Sequences (runs) contained 
	 * in the group.
	 * 
	 * NOTE: This should be a list that can grow dynamically
	 */
	private Sequence[] seqSet;

	/**
	 * The seqSetIndex is set to the array index of the latest Sequence.  -1
	 * means the array is empty and there are no workflows
	 * 
	 * NOTE: This will likely be DEPRECATED when implementing a dynamic
	 * Sequence list.
	 */
	private int seqSetIndex = -1;

	/**
	 * The workflows attribute holds the set of workflows executed 
	 * against the group.  There may be more than one if/when the 
	 * group of runs is reduced multiple times.
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
	 * This is the constructor for the Group class
	 */
	public Group() {
		System.out.println("Group() constructor");
		this.sequenceID  = "";
		this.workflowSet[0] = null;
		this.workflowSet[1] = null;
	}

	/**
	 * This is another constructor for the Group class.  It takes a DataSet from which
	 * it can initialize all relevant attributes
	 * 
	 * @param set - the DataSet to use in initializing the attributes
	 * @param seq - the Sequence to associate with the group
	 */
	public Group(DataSet set, Sequence seq) {
		System.out.println("Group() constructor");
		
		this.dataSet = set;   // ?should I create a new DataSet first?
		
		// Get the sequenceID from the DataSet.MetaData
		this.sequenceID = set.getMetaData().getGroupID();
		
		// Get the number of sequences (runs) that are expected
		this.seqSet = new Sequence[set.getMetaData().getSequenceTotal()];
		this.seqSet[0] = seq;
		this.seqSetIndex = 0;
		
		// Initialize the workflowSet
		this.workflowSet[0] = null;
		this.workflowSet[1] = null;
	}

	/**
	 * This is a getter method to return the sequenceID attribute 
	 * which contains the ID for the group
	 * 
	 * @return String - the value of the sequenceID attribute
	 */
	public String getSequenceID() {
		System.out.println("Group.getSequenceID()");
		return this.sequenceID;
	}

	/**
	 * This is a setter method to set the sequenceID attribute 
	 * which contains the ID for the group
	 * 
	 * @param id - id that is used to set the sequenceID attibute
	 */
	public void setSequenceID(String id) {
		System.out.println("Group.setSequenceID(STring id)");
		this.sequenceID = id;
	}

	/**
	 * This is a getter method to return the workflowSet attribute
	 * 
	 * @return Workflow[] - workflowSet attribute
	 */
	public Workflow[] getWorkflowSet() {
		System.out.println("Group.getWorkflowSet()");
		return this.workflowSet;
	}

	/**
	 * This is a setter method to add a new workflow to the workflowSet attribute
	 * 
	 * @param workflow  - the workflow to be added to the workflowSet attribute
	 */
	public void addWorkflow(Workflow workflow) {
		System.out.println("Group.addWorkflow(Workflow workflow)");
		
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
	 * This is a getter method to return the seqSet attribute
	 * 
	 * @return Sequence - the seqSet attribute
	 */
	public Sequence[] getSeqSet() {
		return this.seqSet;
	}

	/**
	 * This is a setter method to add a Sequence to the seqSet attribute
	 * 
	 * @param seqSet the seqSet to set
	 * 
	 * @return void
	 */
	public void addSeq(Sequence seq) {
		System.out.println("Group.addWorkflow(Workflow workflow)");
		
		// If the index is any negative number it means there are no sequences
		if (this.seqSetIndex < 0) {
			this.seqSet[0] = seq;
		}
		else if (this.seqSetIndex < 1) {
			this.seqSet[this.seqSetIndex] = seq;
			this.seqSetIndex++;
		}
		// else do nothing.  Need to think about how to throw an error
		// however this is a temporary implementation for the Prototype
	}

	/**
	 * This method finds and returns the Sequence specified by the sequence number 
	 * passed in.  Return null if the Sequence is not found
	 * 
	 * @param seqNum  - the number (id) of the sequence to be returned from the group
	 * 
	 * @return Sequence
	 */
	public Sequence findSequence(int seqNum) {
		boolean found = false;
		int i = 0;
		
		for (i = 0; seqNum != seqSet[i].getSequenceNum() && i <= dataSet.getMetaData().getSequenceNumber(); i++) {
			if (seqNum == this.seqSet[i].getSequenceNum()) {
					found = true;
			}
		}
		
		if (found)
			return this.seqSet[i];
		else
			return null;

	}

	/**
	 * This method is to find and return the latest workflow associate with Group
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow() {
		return this.workflowSet[this.workflowIndex];
	}

	/**
	 * This method is to find and return the workflow that matches the id
	 * 
	 * @param id - the group ID to use to fine the requested workflow
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow(String id) {
		System.out.println("Group.findWorkflow(String id)");
		
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

}   // end Group class