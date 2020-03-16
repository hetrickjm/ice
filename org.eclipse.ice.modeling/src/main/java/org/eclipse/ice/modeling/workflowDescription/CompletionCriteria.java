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

import java.util.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED
 * 
 * This class represents (holds) the completion criteria for a WorkflowDescription.
 * 
 * NOTE: At this time this is a concept that is believed to be needed, but the detail has yet to be
 * worked out.
 * 
 * @author John Hetrick
 */
public class CompletionCriteria {

	/**
	 * CURRENTLY THIS ATTRIBUTE IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * The completionMsg attribute is the completion criteria
	 */
	private String completionMsg;

	/**
	 * CURRENTLY THIS ATTRIBUTE IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * The criteria attribute is the completion criteria.  It is a
	 * List that who's elements are to be compared to
	 */
	private List <Criteria> criteriaSet;

	/**
	 * This is the constructor for the CompletionCriteria class
	 */
	public CompletionCriteria() {
		System.out.println("CompleteCriteria() constructor");
		
		this.criteriaSet = new ArrayList();
	}

	/**
	 * This is another constructor for the CompletionCriteria Class.  This constructor takes a
	 * Criteria to initialize the criteriaSet attribute.
	 * @param crit - a Criteria to initialize the criteriaSet attribute
	 */
	public CompletionCriteria(Criteria crit) {
		System.out.println("CompleteCriteria(String msg) constructor");
		
		addCompletionCreiteria(crit);
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to return the criteriaSet attribute
	 */
	public List<Criteria> getCompletionCriteria() {
		System.out.println("CompleteCriteria.getcompletionMsg()");
		
		
		return this.criteriaSet;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to add a Criteria to the criteriaSet attribute
	 * 
	 * @param crit - the message to set the completionMsg to
	 */
	public void addCompletionCreiteria(Criteria crit) {
		System.out.println("CompleteCriteria.addCompletionMsg(Criteria crit)");
		this.criteriaSet.add(crit);
	}

	/**
	 * This method evaluates if the parameter (List of objects) passed in matches the completion criteria. If it does return true else return false
	 * @param status - List of items to compare with the criteria to determine equivalency
	 */
	public boolean isComplete(List <Criteria> status) {
		System.out.println("CompleteCriteria.isComplete(List status)");
		
		// If the size of the two lists is equal then check the values, if not then
		// there is no way status can be complete so return false
		if (status.size() == criteriaSet.size()) {
			int statLen = status.size();
			int critLen = criteriaSet.size();
			boolean found = true;
			boolean done = false;
			
			// Loop through the status criteria and see if they are in the criteriaSet
			for (int i = 0; i < statLen && found == true; i++) {
				for (int j = 0; j < critLen && done == false; j++) {
					if (status.get(i).isMatch(this.criteriaSet.get(j).getCriteria())) {
						done = true;
					}
				}
				
				// Check that there was a match.  If not then stop checking
				if (done != true)
					found = false;
			}
			
			// If all criteria were matched return true else return false
			if (found == true)
				return true;
			else
				return false;
		}
		else
			// There was a mismatch in the number of elements in the incoming status
			// and the number of criteria in the list.  There is no way for there to
			// be a match
			return false;
	}

}   // end class CompleteCriteria