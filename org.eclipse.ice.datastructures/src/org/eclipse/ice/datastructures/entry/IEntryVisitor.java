/*******************************************************************************
 * Copyright (c) 2012, 2014, 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Alex McCaskey
 *******************************************************************************/
package org.eclipse.ice.datastructures.entry;

import org.eclipse.ice.datastructures.form.painfullySimpleForm.PainfullySimpleEntry;

/**
 * This interface defines the "visitation" routines that implementations of
 * IEntry may use to reveal their types to visitors. It is one part of the
 * Visitor pattern.
 * 
 * @author Alex McCaskey
 *
 */
public interface IEntryVisitor {

	/**
	 * This operation directs a visitor to perform its actions on the IEntryVisitor
	 * as a StringEntry.
	 * 
	 * @param entry
	 *            StringEntry which was originally called by the accept()
	 *            operation
	 */
	public void visit(StringEntry entry);
	
	/**
	 * This operation directs a visitor to perform its actions on the IEntryVisitor
	 * as a FileEntry.
	 * 
	 * @param entry
	 *            FileEntry which was originally called by the accept()
	 *            operation
	 */
	public void visit(FileEntry entry);
	
	/**
	 * This operation directs a visitor to perform its actions on the IEntryVisitor
	 * as a DiscreteEntry.
	 * 
	 * @param entry
	 *            DiscreteEntry which was originally called by the accept()
	 *            operation
	 */
	public void visit(DiscreteEntry entry);
	
	/**
	 * This operation directs a visitor to perform its actions on the IEntryVisitor
	 * as a ExecutableEntry.
	 * 
	 * @param entry
	 *            ExecutableEntry which was originally called by the accept()
	 *            operation
	 */
	public void visit(ExecutableEntry entry);
	
	/**
	 * This operation directs a visitor to perform its actions on the IEntryVisitor
	 * as a ContinuousEntry.
	 * 
	 * @param entry
	 *            ContinuousEntry which was originally called by the accept()
	 *            operation
	 */
	public void visit(ContinuousEntry entry);
	
	/**
	 * This operation directs a visitor to perform its actions on the IEntryVisitor
	 * as a PainfullySimpleEntry.
	 * 
	 * @param entry
	 *            PainfullySimpleEntry which was originally called by the accept()
	 *            operation
	 */
	public void visit(PainfullySimpleEntry entry);
	
	/**
	 * This operation directs a visitor to perform its actions on the IEntryVisitor
	 * as a MultiValueEntry.
	 * 
	 * @param entry
	 *            MultiValueEntry which was originally called by the accept()
	 *            operation
	 */
	public void visit(MultiValueEntry entry);
}
