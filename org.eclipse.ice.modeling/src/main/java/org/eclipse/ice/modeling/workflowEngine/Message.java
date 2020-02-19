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

public class Message {

	/**
	 * DEPRECATE - This is part of the DataSet MetaData
	 */
	private int    srcInstrument;
	/**
	 * DEPRECATE - This is part of the DataSet MetaData
	 */
	private String expId;
	private String cmnd;
	/**
	 * Reference to a data set
	 */
	private DataSet attribute;
	/**
	 * Reference to a data set
	 */
	private DataSet dataSetRef;

	public Message() {
		System.out.println("Message() constructor");
		
		// Init attributes
		this.setExpId("ExpID-12");
		this.setSrcInstrument(3);
		this.setCmnd("blank");
	}   // end Message() constructor

	public int getSrcInstrument() {
		return srcInstrument;
	}   // end Message.getSrcInstrument()

	/**
	 * 
	 * @param id
	 */
	public void setSrcInstrument(int srcInstrument) {
		this.srcInstrument = srcInstrument;
	}   // end Message.setSrcInstrument(int srcInstrument)

	public String getExpId() {
		return expId;
	}   // end Message.getExpId()

	/**
	 * 
	 * @param id
	 */
	public void setExpId(String expId) {
		this.expId = expId;
	}   // end Message.setExpId(STring expId)

	public String getCmnd() {
		return cmnd;
	}   // end Message.getCmnd()

	/**
	 * 
	 * @param id
	 */
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}   // end Message.setCmnd() String cmnd )

	/**
	 * @return the dataSetRef
	 */
	public DataSet getDataSetRef() {
		return dataSetRef;
	}   // end Message.getDataSetRef()

	/**
	 * @param dataSetRef the dataSetRef to set
	 */
	public void setDataSetRef(DataSet dataSetRef) {
		this.dataSetRef = dataSetRef;
	}   // end Message.setDataSetRef(DataSet dataSetRef)

}   // end class Message