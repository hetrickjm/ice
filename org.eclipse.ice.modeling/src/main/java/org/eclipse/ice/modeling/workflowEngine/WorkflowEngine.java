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

import org.eclipse.ice.modeling.actors.*;
import org.eclipse.ice.modeling.*;
import org.eclipse.ice.modeling.workflow.*;

/**
 * The WorkflowEngine is the class that handles the incoming messages, invoking the correct workflow
 * and managing whatever the workflow needs to do next.  Generally send a message to some external
 * actor like a reducer
 * 
 * @author John Hetrick
 */
public class WorkflowEngine implements IWorkflow {

	/**
	 * The reducer attribute holds a reference to a post processing reducer system that will handle the reduction of the data sets
	 */
	private ReducerStub reducer;
	private Workflow attribute;
	/**
	 * The workflow attribute is the workflow that the workflow engine is working on.  Ideally this will be a set of references to workflows
	 */
	private Workflow workflow;

	/**
	 * Basic WorkflowEngine constructor
	 */
	public WorkflowEngine() {
		System.out.println("WorkflowEngine() constructor");
		
	}   // end WorkflowEngine() constructor

	/**
	 * WorkflowEngine constructor that takes a reference to a reducer and a workflow
	 * @param reducer Reference to a reducer system
	 * @param workflow A workflow the workflow engine will work with
	 */
	public WorkflowEngine(ReducerStub reducer, Workflow workflow) {
		System.out.println("WorkflowEngine(ReducerStub reducer, Workflow workflow) constructor");
		
		this.reducer  = reducer;
		this.workflow = workflow;
		
	}   // end WorkflowEngine() constructor

	/**
	 * 
	 * @param msg
	 */
	public void handleMsg(Message msg) {
		System.out.println("WorkflowEngine.handleMsg()");
		
		Message msgOut;
		
		// Determine which workflow the incoming msg is associated wtih
		// The incoming message has a refernce to a DataSet which contains the MetaData
		//Workflow wrkfl = this.determineWorkflow(msg.getDataSetRef().getMetaData());
		
		
		// Ask the workflow for the next outgoing message
		// A return of null indicates no follow on message
		msgOut = workflow.handleMsg(msg);
		
		if (msgOut != null) {
			reducer.processMessage(msgOut, this);
		}
		
	}   // end WorkflowEngine.handleMsg(Message msg)

	public ReducerStub getReducer() {
		return this.reducer;
	}   // end WorkflowEngine.getReducer()

	/**
	 * 
	 * @param reducer
	 */
	public void setReducer(ReducerStub reducer) {
		this.reducer = reducer;
	}   // end WorkflowEngine.setReducer(ReducerStub reducer)

	/**
	 * 
	 * @param metaData
	 */
	public Workflow determineWorkflow(MetaData metaData) {
		System.out.println("WorkflowEngine.determineWorkflow(MetaData metaData)");
		return this.workflow;
		
	}

}   // end class WorkflowEngine