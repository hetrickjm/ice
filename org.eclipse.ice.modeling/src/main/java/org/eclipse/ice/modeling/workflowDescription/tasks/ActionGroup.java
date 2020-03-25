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

import org.eclipse.ice.modeling.workflowDescription.*;

/**
 * The ActionInstruction class specializes the Action class and
 * contains instructions that are to be followed when invoked
 * 
 * @author John Hetrick
 */
public class ActionGroup extends Action {

	/**
	 * The instruction attribute is an enumeration indicating which 
	 * action this Action indicates
	 */
	private InstructionGroup instruction;

	/**
	 * This is the constructor for the ActionInstruction class
	 */
	public ActionGroup() {
		System.out.println("ActionInstruction() constructor");
		this.instruction = InstructionGroup.REDUCE;
	}

	/**
	 * This is another constructor for the ActionInstruction class that takes
	 * an argument of enum that identifies the instruction this is
	 * @param instr - the Instruction to set the instruction attribute indicating what instruction this action is
	 */
	public ActionGroup(InstructionGroup instr) {
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
	 * 
	 * @param instruction - the specific Instruction to use in setting the 
	 * instruction attribute
	 * 
	 * @return void
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

}