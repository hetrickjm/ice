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
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(Sequence.class);
	
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
	 */
	private List <Workflow> workflowSet;

	/**
	 * This is the constructor for the Sequence class
	 */
	public Sequence() {
		logger.debug("Sequence() constructor");
		this.workflowSet = new ArrayList <Workflow>();
		
		this.sequenceNum = -1;   // -1 is not a valid sequence number so init to invalud value
	}

	/**
	 * This is another constructor for the Sequence class that takes in 
	 * the SequenceNum as a parameter
	 * 
	 * @param set  - the DataSet to associate with the Sequence
	 */
	public Sequence(DataSet set) {
		logger.debug("Sequence(DataSet set) constructor");
		
		this.dataSet     = set;
		this.sequenceNum = set.getMetaData().getSequenceNumber();
		
		this.workflowSet = new ArrayList <Workflow>();
		
	}

	/**
	 * This is a getter method to return the sequenceNum attribute which 
	 * is the number of this data set in the sequence it is a part of
	 * 
	 * @return int - the value of the sequenceNum attribute
	 */
	public int getSequenceNum() {
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
	 * @return List <Workflow> - workflowSet attribute
	 */
	public List <Workflow> getWorkflowSet() {
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
		this.workflowSet.add(workflow);
	}

	/**
	 * This method is to find and return the latest Workflow in the set.  Presumably
	 * this is the latest workflow to be created and executed
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow() {
		return this.workflowSet.get(this.workflowSet.size() - 1);
	}

	/**
	 * This method is to find and return the Workflow associated with the id
	 * 
	 * @param id - the id used to find the desired workflow
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow(String id) {
		logger.debug("Sequence.findWorkflow(String id)");
		
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
	 * This method returns the number of workflows associated with group
	 * 
	 * @return - the number of workflows in the group
	 */
	public int workflowCount() {
		return this.workflowSet.size();
	}

}   // end class Sequence