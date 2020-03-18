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
	 * The groupSet attribute holds a set of Groups that represent a set of experiment 
	 * groups (or run sets).  Each group contains a set of sequences (runs)
	 */
	private List <Group> groupSet;
	
	/**
	 * This attribute is the ID of the instrument the on which the experiment was conducted
	 */
	private String instrumentID;

	/**
	 * This is the constructor for the Experiment class
	 */
	public Experiment() {
		System.out.println("Experiment() constructor");
		
		this.groupSet = new ArrayList <Group>();
		
		this.setExpID("EXP-0");
		
	}
	
	/**
	 * This is another constructor for the Experiment class.  It takes an experiment ID 
	 * and a Group.
	 * 
	 * @param id  - id used to set the expID attribute
	 * @param inst - id of the instrument
	 * 
	 * @param group  - a the initial group of the experiment
	 */
	public Experiment(String id, String inst, Group group) {
		System.out.println("Experiment() constructor");
		
		// Init the attribute to hold a set of groups
		this.groupSet = new ArrayList <Group>();
		this.setExpID(id);
		this.setInstrumentID(inst);
		this.addGroup(group);
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
	public List <Group> getGroups() {
		System.out.println("Experiment.getGroups()");
		
		return this.groupSet;
	}

	/**
	 * This is a setter method to add a group to the groupSet attribute
	 * @param group  - group to add to the groupSet attribute
	 */
	public void addGroup(Group group) {
		System.out.println("Experiment.addGroup()");
		this.groupSet.add(group);
	}

	/**
	 * This is a getter method to return the groupSet attribute
	 */
	public Group getGroup(int index) {
		System.out.println("Experiment.getGroup(int index)");
		
		if (index <= this.groupSet.size())
			return this.groupSet.get(index);
		else
			return null;
	}

	/**
	 * This method finds and returns the group identified by the group ID (String) passed in.
	 * If the group is not found return null.
	 * 
	 * @return Group
	 * @param id  - the id of the group to be found and returned
	 */
	public Group findGroup(String id) {
		System.out.println("Experiment.findGroup(String id)");
		
		// Loop through the set of groups looking for matching ID
		for (int i = 0; i < this.groupSet.size(); i++) {
			if ( id == this.groupSet.get(i).getSequenceID())
				return this.groupSet.get(i);
		}
		
		return null;
	}

	/**
	 * This is a getter method to return the instrumentID attribute
	 * @return -ID of the instrument the experiment was run on
	 */
	public String getInstrumentID() {
		return this.instrumentID;
	}

	/**
	 * This is a setter method to set the instrumentID attribute
	 * @param id - the id of the instrument that conducted the experiment
	 */
	public void setInstrumentID(String id) {
		this.instrumentID = id;
	}

}   // end class Experiment