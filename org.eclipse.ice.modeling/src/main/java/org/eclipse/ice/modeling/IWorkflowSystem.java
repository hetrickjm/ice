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
package org.eclipse.ice.modeling;

import org.eclipse.ice.modeling.workflowEngine.*;

/**
 * This is an interface class for the Workflow System.  It is the primary interface
 * to be used by any actor interacting with the system.
 * 
 * @author John Hetrick
 */
public interface IWorkflowSystem {

	/**
	 * The handleMsg method is used by any external entity that wants the
	 * Workflow System to handle, meaning process, some message.  As this is an interface
	 * class the class realizing this interface must provide an implementation.
	 * 
	 * @param msg
	 * @return - CommandStatus indicating the result of the function.
	 */
	void handleMsg(Message msg);

}