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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED
 * 
 * This class specializes Criteria to be a criteria that is of type String.
 * 
 * NOTE: At this time this is a concept that is believed to be needed, but 
 * the detail has yet to be worked out.
 * 
 * @author John Hetrick
 */
public class StringCriteria extends Criteria {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(StringCriteria.class);
	
	/**
	 * This is the constructor for the StringCriteria class
	 */
	public StringCriteria() {
		super();
		logger.debug("StringCriteria.() constructor");
		
	}

	/**
	 * This is another constructor for the StringCriteria class that takes a string to make into a Criteria
	 * @param str - String to set as the Criteria
	 */
	public StringCriteria(String str) {
		super();
		logger.debug("StringCriteria.(String str) constructor");
		
		super.setCriteria(str);
	}

	/**
	 * This method determines if the passed in string matches the current criteria string.  
	 * THis method overrides the same method from the parent class
	 * 
	 * @param crit - the criteria value to match with the Criteria
	 */
	public boolean isMatch(Object crit) {
		
		// The Object passed in should be a String
		if ((String) crit == (String) super.getCriteria())
			return true;
		else
			return false;
	}

}   // end StringCriteria class