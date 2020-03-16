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
package org.eclipse.ice.modeling.workflowDescription;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED
 * 
 * The criteria class is used to be as generic criteria that is used in 
 * creating lists of criteria.  This class is expected to be specialized 
 * so there can be criteria of specific types.
 * 
 * NOTE: At this time this is a concept that is believed to be needed, but 
 * the details have yet to be worked out.
 * 
 * @author John Hetrick
 */
public class Criteria {

	/**
	 * The criteria attribute contains a criteria to be use.  It is an Object 
	 * so it can be any class.
	 */
	private Object criteria;

	/**
	 * This is the constructor for the Criteria class
	 */
	public Criteria() {
		System.out.println("Criteria.() constructor");
		this.criteria = null;
	}

	/**
	 * This is a getter method to return the value of the criteria attribute.  
	 * Note this is a generic Object.  When specialized it will be of a specific type
	 * @return - generic criteria
	 */
	public Object getCriteria() {
		return this.criteria;
	}

	/**
	 * This is a setter method to set the criteria attribute.  This takes an 
	 * Object that when specialized could be of any type
	 * 
	 * @param value - the Object to use to set the criteria attribute
	 */
	public void setCriteria(Object value) {
		this.criteria = value;
	}

	/**
	 * This method determines of the passed in value matches the current criteria.  
	 * It is anticipated that this method will be over ridden in a class that 
	 * specializes this class
	 * 
	 * @param crit - the criteria value to match with the Criteria
	 */
	public boolean isMatch(Object crit) {
		if (crit == criteria)
			return true;
		else
			return false;
	}

}