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
import org.eclipse.ice.modeling.workflowEngine.*;

/**
 * The Experiment is a structure to hold information about an experiment.
 * 
 * @author John Hetrick
 */
public class Experiment {

	/**
	 * The expID attribute is the ID of the experiment
	 */
	private String   expID;
	
	/**
	 * The groupSet attribute holds a set of Groups that represent a set of experiment groups (or run sets).  Each group contains a set of sequences (runs)
	 */
	private Group[] groupSet;

	/**
	 * This is the constructor for the Experiment class
	 */
	public Experiment() {
		super();
		System.out.println("Experiment() constructor");
		
		this.setExpID("ExpID-42");
		
	}
	
	/**
	 * This is a getter method to return the expID attribute
	 * 
	 * @return - expID attribute
	 */
	public String getExpID() {
		System.out.println("Experiment.getExpID()");
		return this.expID;
	}

	/**
	 * This is a setter method to set the expID attribute
	 * 
	 * @param id  - id to use in setting the expID attribute
	 */
	public void setExpID(String id) {
		System.out.println("Experiment.setExpID()");
		this.expID = id;
	}

	/**
	 * This is a getter method to return the groupSet attribute
	 */
	public Group[] getGroups() {
		System.out.println("Experiment.getGroups()");
		return this.groupSet;
	}

	/**
	 * This is a setter method to add a group to the groupSet attribute
	 * @param group  - group to add to the groupSet attribute
	 */
	public void addGroup(Group group) {
		System.out.println("Experiment.addGroup()");
		this.groupSet[0] = group;
	}

	/**
	 * This is another constructor for the Experiment class.  It takes an experiment ID and a Group.
	 * @param id  - id used to set the expID attribute
	 * @param group  - a the initial group of the experiment
	 */
	public Experiment(String id, Group group) {
		// TODO - implement Experiment.Experiment
		throw new UnsupportedOperationException();
	}

	/**
	 * This method finds and returns the group identified by the group ID (String) passed in.
	 * If the group is not found return null.
	 * 
	 * @return Group
	 * @param id  - the id of the group to be found and returned
	 */
	public Group findGroup(String id) {
		// TODO - implement Experiment.findGroup
		throw new UnsupportedOperationException();
	}

}   // end class Experiment