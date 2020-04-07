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
package org.eclipse.ice.modeling.workflowDescription.action;

import org.eclipse.ice.modeling.workflowDescription.*;

/**
 * The ActionInstruction class specializes the Action class and
 * contains instructions that are to be followed when invoked
 * 
 * @author John Hetrick
 */
public class GroupAction extends Action {

	/**
	 * The instruction attribute is an enumeration indicating which 
	 * action this Action indicates
	 */
	private InstructionGroup instruction;

	/**
	 * This is the constructor for the ActionInstruction class
	 */
	public GroupAction() {
		System.out.println("ActionInstruction() constructor");
		this.instruction = InstructionGroup.REDUCE;
	}

	/**
	 * This is another constructor for the ActionInstruction class that takes
	 * an argument of enum that identifies the instruction this is
	 * @param instr - the Instruction to set the instruction attribute indicating what instruction this action is
	 */
	public GroupAction(InstructionGroup instr) {
		System.out.println("ActionInstruction(InstructionGroup instr) constructor");
		
		this.instruction = instr;
	}

	/**
	 * This is a getter method to return the instruction attribute
	 * @return - Instruction enumeration value indicating what the action is
	 */
	public InstructionGroup getInstruction() {
		System.out.println("ActionInstruction.getInstruction()");
		return this.instruction;
	}

	/**
	 * This is a setter method to set the instruction attribute
	 * @param inst
	 */
	public void setInstruction(InstructionGroup inst) {
		System.out.println("ActionInstruction.setInstruction(InstructionGroup inst)");
		this.instruction = inst;
	}

	/**
	 * The execute method is responsible for performing the action when invoked.
	 */
	public Object execute() {
		System.out.println("ActionInstruction.execute()");
		return null;
	}

	/**
	 * This execute method takes a parameter and returns an object.
	 * @param obj - generic parameter used to pass in information needed to complete the
	 * action
	 */
	public Object execute(Object obj) {
		// TODO - implement ActionInstruction.execute
		throw new UnsupportedOperationException();
	}

	/**
	 * CURRENTLY THIS METHOD IS FOR EXPLORATORY PURPOSES AND
	 * MAY BE CHANGED OR DEPRECATED
	 * 
	 * This method is the primary method to make the action take action (execute).
	 * @param arg1 - a generic parameter to pass in required information
	 * @param arg2 - this is a parameter that can be used for anything, but is expected to be an incoming message
	 */
	public Object execute(Object arg1, Object arg2) {
		// TODO - implement ActionGroup.execute
		throw new UnsupportedOperationException();
	}

}