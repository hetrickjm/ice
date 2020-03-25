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
package org.eclipse.ice.modeling.workflowDescription.tasks;

/**
 * The Instruction enumeration contains a set of enumeration literals that identify particular actions that can be taken
 * 
 * @author John Hetrick
 */
public enum InstructionGroup {
	/**
	 * Reduce a Sequence (run)
	 */
	REDUCE,
	/**
	 * Stitch together all reduced Sequences (runs)
	 */
	STITCH
}