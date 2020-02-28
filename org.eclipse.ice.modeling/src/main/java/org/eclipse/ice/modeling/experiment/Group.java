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
	 * The workflows attribute holds the set of workflows executed 
	 * against the group.  There may be more than one if/when the 
	 * group of runs is reduced multiple times.
	 * 
	 * NOTE: This should be a list that can grow dynamically
	 */
	private Workflow[] workflowSet;

	/**
	 * This is the constructor for the Group class
	 */
	public Group() {
		System.out.println("Group() constructor");
		this.sequenceID  = "";
		this.workflowSet = new Workflow[2];
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
		this.workflowSet[0] = workflow;
	}

	/**
	 * This is another constructor for the Group class.  It takes an ID for the group (SequenceID) and the first Sequence
	 * @param id  - the id to set the sequenceID
	 * @param seq0 - the sequence to set as the first sequence is a set of sequences
	 */
	public Group(String id, Sequence seq0) {
		// TODO - implement Group.Group
		throw new UnsupportedOperationException();
	}

	/**
	 * This method finds and returns the Sequence specified by the sequence number passed in.  Return null if the Sequence is not found
	 * 
	 * @return Sequence
	 * @param seqNum  - the number (id) of the sequence to be returned from the group
	 */
	public Sequence findSequence(int seqNum) {
		// TODO - implement Group.findSequence
		throw new UnsupportedOperationException();
	}

	/**
	 * This method is to find and return the latest workflow associate with Group
	 * 
	 * @return Workflow
	 */
	public Workflow findWorkflow() {
		// TODO - implement Group.findWorkflow
		throw new UnsupportedOperationException();
	}

}