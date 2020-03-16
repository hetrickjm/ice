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
 * The WorkflowDescriptionType enumeration identifies the types of workflow description that are possible.  Each
 * WorkfloDescription should have an attribute that is set to one of these enumerations literals
 * 
 * @author John Hetrick
 */
public enum WorkflowDescriptionType {
	/**
	 * This literal identifies a workflow description for a Group of sequences (runs)
	 */
	GROUP,
	
	/**
	 * This literal identifies a workflow description for a Sequences (run)
	 */
	SEQ,
	/**
	 * FOR EXPLORATION & TESTING PURPOSES
	 * 
	 * Indicates the workflow description of no type.  Anticipated this may be used only for initialization.
	 */
	NONE
}