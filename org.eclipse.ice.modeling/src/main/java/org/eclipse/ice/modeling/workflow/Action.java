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
package org.eclipse.ice.modeling.workflow;

import org.eclipse.ice.modeling.workflowEngine.*;

public class Action {

	public Action() {
		System.out.println("Action() constructor");
	}

	/*
	 * I think this should be virtual function so it has to be implemented by
	 * the concrete class.
	 */
	public Message execute() {
		System.out.println("Action.execute()");
		return null;
	}

}