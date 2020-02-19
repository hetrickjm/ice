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
import org.eclipse.ice.modeling.actors.*;
import org.eclipse.ice.modeling.workflow.*;

/**
 * This is the primary class that represent the Workflow System.  It's primary function
 * is to get the workflow system setup and initialized to begin running.
 * 
 * NOTE: This system is in an exploratory mode and is likely to change quite a bit.
 * 
 * @author John Hetrick
 */
public class WorkflowSystem implements IWorkflow {
	
	/**
	 * An initial workflow to test the concept of the workflow engine.
	 * FUTURE: There should be a factory to creat workflows on demand when required.
	 */
	private Workflow wrkflow;
	
	/**
	 * The workflow engine is the internal system component that manages the 
	 * processing of the correct workflow(s) based on incoming messages
	 */
	private WorkflowEngine wfEng;
	
	/**
	 * The Transition Service attribute holds a referent to the Transition service.
	 * 
	 * I believe this can/should be DEPRECATED
	 */
	private TransSrvcStub transSrvc;
	
	/**
	 * The reducer attribute holds a reference to a post processing reducer that 
	 * this system will send data set reduction workflow message to.  Currently 
	 * this is proof of concept code.  For the real system there could be many reducers
	 */
	private ReducerStub reducer; 
	
	/**
	 *  This is a constructor for the Workflow System.  It take no parameters so the 
	 *  attributes are set with default values and should be set manually
	 */
	public WorkflowSystem() {
		
		System.out.println("WorkflowSystem.main()");
		
		// Initialize the system
		initSystem();
		
	}   // end WorkflowSystem() constructor
	
	/**
	 * This is one of the Workflow System constructors that can take references to the 
	 * Transition Service and Reducer.
	 * 
	 * @param trans Reference to the Translation Service
	 * @param reducers Reference to a post processing reducer system
	 */
	public WorkflowSystem(TransSrvcStub trans, ReducerStub reducers) {
		
		System.out.println("WorkflowSystem(TransSrvcStub trans, ReducerStub reducers) constructor");
		
		// Set the Transition service and the reducer.
		// FUTURE: There will be a set of reducers
		this.setTransSrvc(trans);
		this.setReducer(reducers);
		
		// Initialize the system
		initSystem();
	}   // end WorkflowSystem(TransSrvcStub trans, ReducerStub reducers) Constructor
	
	/**
	 * This method handles the initialization of the Workflow System.
	 * 
	 * @return void
	 */
	public void initSystem () {
		
		System.out.println("WorkflowSystem.initSystem()");
		
		// Create an Experiment workflow and a Run workflow.  Make the run a child
		// of the Experiment workflow
		// Note: The Composite patter is used for workflow.  Both WxpWF and RunWF 
		// inherit from Workflow so they share the same interface.
		RunSetWF runSet = new RunSetWF();
		RunWF    run    = new RunWF();
		
		runSet.setChildWFs(run);
		
		// Init the major objects
		wrkflow = runSet;
		wfEng   = new WorkflowEngine( reducer, wrkflow);

	}   // end WorkflowSystem.initSystem()
	
	/**
	 * Getter for the Translation Service attribute
	 * 
	 * @return TransSrvcStub
	 */
	public TransSrvcStub getTransSrvc() {
		return this.transSrvc;
	}   // end WorkflowSystem.getTransSrvc()

	/**
	 * Setter for the Translation Service attribute
	 * 
	 * @param transSrvc Reference to a Translation Service
	 * @return void
	 */
	public void setTransSrvc(TransSrvcStub transSrvc) {
		this.transSrvc = transSrvc;
	}   // end WorkflowSystem.setTransSrvc(TransSrvcStub transSrvc)
	
	/**
	 * Getter for the reducer attribute
	 * 
	 * @return ReducerStub
	 */
	public ReducerStub getReducer() {
		return this.reducer;
	}   // end WorkflowSystem.getReducer()

	/**
	 * Setter for the Reducer attribute
	 * 
	 * @param reducer Reference to a Reducer system
	 * @return void
	 */
	public void setReducer(ReducerStub reducer) {
		this.reducer = reducer;
	}   // end WorkflowSystem.setReducer(ReducerStub reducer)

	/**
	 * This method is to satisfy the interface but in a real system it should 
	 * not resolve to this class.
	 * 
	 * @param msg Incoming message that needs to be processed.
	 * @return void
	 */
	public void handleMsg(Message msg) {
		System.out.println("WorkflowSystem.handleMsg(Message msg)");
		
	}   // end WorkflowSystem.handleMsg(Message msg)

	/**
	 * Getter for the workflow engine attribute.  This method returns a class 
	 * with the IWorkflow interface that the Translation Service uses
	 * 
	 * @return WorkflowEngine
	 * @return void
	 */
	public IWorkflow getWfEng() {
		System.out.println("WorkflowSystem.getWfEng()");
		return wfEng;
	}   // end WorkflowSystem.getWfEng()
	
}   // end class WorkflowSystem