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
package org.eclipse.ice.modeling.workflowEngine;

import org.eclipse.ice.modeling.workflowDescription.*;

/**
 * THIS CLASS IS ONLY FOR CONCEPT EXPLORATION
 * This class is a crude way to associate a Meta Data with a Workflow Description.  For testing
 * this is a simple way to determine what Workflow Description to use against a Data Set.
 */
public class Mapper {

	/**
	 * This attribute identifies the Meta Data criteria that is used to identify a Workflow Description for a Data Set.  It should be one of the following combinations or information:
	 *    - instrumentID, experimentID, dataType
	 *    - instrumentID, experimentID
	 *    - instrumentID  (default)
	 */
	private String metaDataKey;
	
	/**
	 * This attribute is a Workflow Description that is to be paired with the criteria (Meta Data) to be used on specific Data Sets
	 */
	private WorkflowDescription workflowDescription;

	/**
	 * This is the constructor for the Mapper class.
	 * It initializes all attributes to null or ""
	 */
	public Mapper() {
		System.out.println( "Mapper() constructor" );
		
		this.metaDataKey = "";
		this.workflowDescription = null;
	}

	/**
	 * This is another constructor for the DS2WDPair class.
	 * It initializes all attributes to those that are passed in.
	 * @param id - ID for the metaDataID attribute
	 * @param wd - WorkflowDescription for the workflowDescription attribute
	 */
	public Mapper(String id, WorkflowDescription wd) {
		System.out.println( "Mapper(String id, WorkflowDescription wd) constructor" );
		
		this.setMetaDataKey(id);
		this.setWorkflowDescription(wd);
	}

	/**
	 * This is a getter method to return the metaDataKey attribute.
	 */
	public String getMetaDataKey() {
		return this.metaDataKey;
	}

	/**
	 * This is a setter method to set the metaDataKey attribute.
	 * @param key - key to use in setting the metaDataKey attribute
	 */
	public void setMetaDataKey(String key) {
		this.metaDataKey = key;
	}

	/**
	 * This is a getter method to return the workflowDescription attribute.
	 */
	public WorkflowDescription getWorkflowDescription() {
		return this.workflowDescription;
	}

	/**
	 * This is a setter method to set the workflowDescription attribute.
	 * @param wd - the WorkflowDescription to be used to set the workflowDescription attributre
	 */
	public void setWorkflowDescription(WorkflowDescription wd) {
		this.workflowDescription = wd;
	}

}