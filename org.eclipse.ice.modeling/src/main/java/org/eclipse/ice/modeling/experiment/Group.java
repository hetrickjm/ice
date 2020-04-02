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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(Group.class);
	
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
	 * The sequenceID attribute is the ID for the sequence set (or 
	 * group ID or run set ID).  This is the same as the sequenceID 
	 * on the first data set for a particular run set.
	 */
	private int sequenceTotal;
	/**
	 * The seqSet attribute holds the set of Sequences (runs) contained 
	 * in the group.
	 * 
	 * NOTE: This should be a list that can grow dynamically
	 */
	private List <Sequence> seqSet;

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
	private List <Workflow> workflowSet;

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
		logger.debug("Group() constructor");
		
		// Init Attributes
		this.sequenceID  = "";
		this.seqSet      = new ArrayList <Sequence>();   // init set of Sequences
		this.workflowSet = new ArrayList <Workflow>();   // init set of Workflows
	}

	/**
	 * This is another constructor for the Group class.  It takes a DataSet from which
	 * it can initialize all relevant attributes
	 * 
	 * @param set - the DataSet to use in initializing the attributes
	 * @param seq - the Sequence to associate with the group
	 */
	public Group(DataSet set, Sequence seq) {
		logger.debug("Group(DataSet set, Sequence seq) constructor");
		
		// Init Attributes
		this.sequenceID  = "";
		this.seqSet      = new ArrayList <Sequence>();   // init set for Sequences
		this.workflowSet = new ArrayList <Workflow>();   // init set for Workflows

		this.dataSet = set;   // ?should I create a new DataSet first?
		
		// Init the sequenceID from the DataSet.MetaData
		this.sequenceID = set.getMetaData().getGroupID();
		
		// Init the number of sequences (runs) that are expected
		this.sequenceTotal = dataSet.getMetaData().getSequenceTotal();
		
		// Add the initial sequence to the seqSet
		this.seqSet.add(seq);
		
	}

	/**
	 * This is a getter method to return the sequenceID attribute 
	 * which contains the ID for the group
	 * 
	 * @return String - the value of the sequenceID attribute
	 */
	public String getSequenceID() {
		return this.sequenceID;
	}

	/**
	 * This is a setter method to set the sequenceID attribute 
	 * which contains the ID for the group
	 * 
	 * @param id - id that is used to set the sequenceID attibute
	 */
	public void setSequenceID(String id) {
		this.sequenceID = id;
	}

	/**
	 * This is a getter method to return the workflowSet attribute
	 * 
	 * @return Workflow[] - workflowSet attribute
	 */
	public List <Workflow> getWorkflowSet() {
		return this.workflowSet;
	}

	/**
	 * This is a setter method to add a new workflow to the workflowSet attribute
	 * 
	 * @param workflow  - the workflow to be added to the workflowSet attribute
	 */
	public void addWorkflow(Workflow workflow) {
		this.workflowSet.add(workflow);
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
		
		MetaData meta      = set.getMetaData();
		this.sequenceID    = meta.getGroupID();         // Init the sequenceID from the DataSet.MetaData
		this.sequenceTotal = meta.getSequenceTotal();   // Init the number of sequences (runs) that are expected
	}

	/**
	 * This is a getter method to return the seqSet attribute
	 * 
	 * @return Sequence - the seqSet attribute
	 */
	public List <Sequence> getSeqSet() {
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
		this.seqSet.add(seq);
	}

	/**
	 * This method finds and returns the Sequence specified by the sequence number 
	 * passed in.  Return null if the Sequence is not found
	 * 
	 * @param seqNum  - the number (id) of the sequence to be returned from the group
	 * @return 
	 * 
	 * @return Sequence
	 */
	public Sequence findSequence(int seqNum) {
		logger.debug("Group.findSequence(int seqNum)");

		// Look for a sequence who's sequence number matches the passed in value
		// NOTE: this should be equivalent to the index, but for now search the list to be safe.
		if (seqNum < this.seqSet.size()) {
			if (seqNum == this.seqSet.get(seqNum).getSequenceNum())
				return this.seqSet.get(seqNum);
			
			// Search for the right sequence since the seqNum is not equivalent to the index
			for (int i = 0; i < this.seqSet.size(); i++) {
				if (seqNum == this.seqSet.get(i).getSequenceNum())
					return this.seqSet.get(i);
			}
		}
		
		// The seqNum is out of bounds
		return null;
	}

	/**
	 * This method is to find and return the latest workflow associate with Group
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow() {
		logger.debug("Group.findWorkflow()");
		
		// Check that there are any workflows.  If not return null.
		if (this.workflowSet.size() < 1)
			return null;
		else
			return this.workflowSet.get(this.workflowSet.size() - 1);
		
	}

	/**
	 * This method is to find and return the workflow that matches the id
	 * 
	 * @param id - the group ID to use to fine the requested workflow
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow(String id) {
		logger.debug("Group.findWorkflow(String id)");
		
		Workflow wf  = null;
		boolean done = false;
		
		for (int i = 0; (i < this.workflowSet.size())  && !done; i++) {
			if (id == this.workflowSet.get(i).getWorkflowID()) {
				wf = this.workflowSet.get(i);
				done = true;
			}
		}
		
		return wf;
	}

	/**
	 * This method returns the number of sequences contained in the group
	 * 
	 * @return - the number of workflows in the group
	 */
	public int seqCount() {
		return this.seqSet.size();
	}

	/**
	 * This method returns the number of workflows associated with group
	 * 
	 * @return - the number of workflows in the group
	 */
	public int workflowCount() {
		return this.workflowSet.size();
	}

}   // end Group class