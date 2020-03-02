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

import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflowDescription.*;
import org.eclipse.ice.modeling.workflowEngine.*;

/**
 * THIS CLASS IS PART OF THE WORKFLOW CONCEPT THAT IS BEING EXPLORED.
 * 
 * Workflow is the entity that is responsible for managing the processing of a data set.
 * It is an abstract class which will be instantiated by a concrete class.  It is part
 * of composition pattern so clients only work with the Workflow interface and do not
 * need to know anything about the concrete classes or their interfaces.
 * 
 * @author John Hetrick
 */
public class Workflow {

	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EX{LORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The wfState attribute holds the current state of the workflow
	 */
	private String wfState;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EX{LORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The wfID attribute holds the ID of the workflow
	 */
	private String wfID;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EX{LORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The dataSet attribute holds the Data Set associated with the workflow
	 */
	private DataSet dataSet;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EX{LORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The procedure attribute holds the WorkflowDescription associated with the workflow
	 */
	private WorkflowDescription procedure;
	
	/**
	 * THIS ATTRIBUTE IS PART OF THE WORLFLOW  EX{LORATION AND MAY BE
	 * CHANGED OR DEPRECATED
	 * 
	 * The currentStep attribute holds the current step of the WorkflowDescription the workflow is currently on
	 */
	private Task currentStep;

	/**
	 * This is the constructor for the Workflow class
	 */
	public Workflow() {
		System.out.println("Workflow() constructor");
		
		// initialize the attributes
		wfState = "";
		wfID    = "Workflow-ID";
	}   // end Workflow() constructor

	/**
	 * This is another constructor for the Workflow class.  It takes the DataSet and 
	 * WorkflowDescription that need to be bound together for the Workflow
	 * 
	 * @param set  - the DataSet to bind with the WorkflowDescription in the Workflow
	 * @param description  - the WorkflowDescription to bind with the DataSet in the Workflow
	 * 
	 */
	public Workflow(DataSet set, WorkflowDescription description) {
		System.out.println("Workflow(DataSet set, WorkflowDescription description) constructor");
		
		// Need a way to create a unique ID for the Workflow
		// Ask Joe or Jay
		this.wfID      = "Workflow-ID";
		this.dataSet   = set;
		this.procedure = description;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the getWfID attribute
	 * 
	 * @return String
	 */
	public String getWfID() {
		return this.wfID;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the getWfID attribute
	 * @param id  - id to use in setting the setWfID attribute
	 */
	public void setWfID(String id) {
		this.wfID = id;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the dataSet attribute
	 * 
	 * @return DataSet
	 */
	public DataSet getDataSet() {
		return dataSet;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the dataSet attribute
	 * @param data - data is the Data Set to use to set the dataSet attribute
	 * 
	 * @return void
	 */
	public void setDataSet(DataSet set) {
		this.dataSet = set;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the procedure attribute
	 * 
	 * @return WorkflowDescription
	 */
	public WorkflowDescription getProcedure() {
		return procedure;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the procedure attribute
	 * @param proc - proc is the used to set the procedure attribute
	 * 
	 * @return void
	 */
	public void setProcedure(WorkflowDescription proc) {
		this.procedure = proc;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a getter method to returning the currentStep attribute
	 * 
	 * @return Task
	 */
	public Task getCurrentStep() {
		return currentStep;
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This is a setter method to set the currentStep attribute
	 * @param step - step is used to set the currentStep attribute
	 * 
	 * @return void
	 */
	public void setCurrentStep(Task step) {
		this.currentStep = step;
	}

	/**
	 * This method is invoked by the workflow engine to have the workflow process an
	 * incoming message or action
	 * 
	 * This is the method from should be is overridden.
	 * 
	 * @param msgIn - msgIn is the incoming message to be recognized and then to take
	 * action on
	 * 
	 * @return Message
	 */
	public Message handleMsg(Message msgIn) {
		System.out.println("Workflow.nextMsg()");
		System.out.println("   msgIn: " + msgIn.toString());
		
		Message msgOut = new Message();
		msgOut.setMsgType("Message out from WF");
		
		System.out.println("   msgOut: " + msgOut.toString());
		
		return msgOut;
	}

}   // end Workflow class 