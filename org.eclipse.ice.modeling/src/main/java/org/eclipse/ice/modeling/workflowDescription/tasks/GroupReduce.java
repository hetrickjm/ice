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

import org.eclipse.ice.modeling.workflow.*;
import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflowEngine.*;

public class GroupReduce extends GroupTask {

	/**
	 * This is the constructor for the GroupReduce class
	 */
	public GroupReduce() {
		super();
		System.out.println("GroupReduce() constructor");
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupReduce execute the action(s) associated
	 * with the Task.
	 */
	public Object execute() {
		System.out.println("GroupReduce.execute()");
		
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupReduce execute the action(s) associated
	 * with the Task.  This method implements how to invoke child workflows associated
	 * with sequences that need to be reduced
	 * 
	 * NOTE: consider renaming to "execute()"
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(Object obj) {
		System.out.println("GroupReduce.execute(Object obj)");
		
		return null;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the GroupReduce execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * Workflow so the Task can us it to persist state
	 * 
	 * @param workflow - the workflow that provides context and is used to capture
	 * and maintain state
	 * @param obj - generic parameter to be used in executing the task. For this class
	 * the obj should be a Message.
	 * 
	 * @return Object - 
	 */
	public Object execute(Workflow wf, Object obj) {
		System.out.println("GroupReduce.execute(Workflow workflow, Object obj)");
		
		Message msgIn = null,
		        msgOut = null;
		
		// Cast the Workflow to a GroupWF
		GroupWF gwf = (GroupWF) wf;
		
		// Cast the Object to a Message
		msgIn = (Message) obj;
		
		// Get the correct ChildWF
		String key = msgIn.getDataSetRef().getMetaData().getInstrumentID() +
				"/" + msgIn.getDataSetRef().getMetaData().getExperimentID() +
				"/" + msgIn.getDataSetRef().getMetaData().getGroupID() +
				"/WFS-" + msgIn.getDataSetRef().getMetaData().getSequenceNumber();

		Workflow childWF = gwf.getChildWorkflowSet(key);
		
		// Determine the state of the child WF
		if (!childWF.isComplete()) {
			msgOut = childWF.handleMsg(msgIn);
		}
		
		// Have the Child WF handle the message
		
		return msgOut;
		
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is to have the Task execute the action(s) associated
	 * with the Task.  Since that Task persists no state, pass in the
	 * Workflow so the Task can us it to persist state
	 * @param taskStatus - the TaskStatus to set as needed
	 * @param obj - generic parameter to be used in executing the task
	 */
	public Object execute(TaskStatus taskStatus, Object obj) {
		// TODO - implement GroupReduce.execute
		throw new UnsupportedOperationException();
	}

}