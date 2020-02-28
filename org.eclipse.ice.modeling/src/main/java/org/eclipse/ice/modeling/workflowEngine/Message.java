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

import org.eclipse.ice.modeling.experiment.*;

/**
 * The Message class represents any message that has been received from an actor or needs to be sent to an actor.  It is not clear at this point if there needs to be specializations of Message or not, but I suspect there will.
 * 
 * @author John Hetrick
 */
public class Message {
	/**
	 * The msgType attribute represents a command or request that specifies what the message is about.  Note: This will likely change as protocols are identified and real messages are specified.  For now it serves for testing concepts
	 */
	private String msgType;
	
	/**
	 * Reference to a data set
	 */
	private DataSet dataSetRef;

	/**
	 * This is the constructor for the Message class
	 */
	public Message() {
		System.out.println("Message() constructor");
		
		// Init attributes
		this.setMsgType("blank");
	}
	
	/**
	 * 
	 * This is a getter method for the msgType attribute that specifies the type of message
	 */
	public String getMsgType() {
		return msgType;
	}   // end Message.getCmnd()

	/**
	 * This is a setter method for msgType attribute that specifies the type of message
	 * @param cmnd
	 */
	public void setMsgType(String cmnd) {
		this.msgType = cmnd;
	}   // end Message.setCmnd() String cmnd )

	/**
	 * This is a getter method to return the dataSetRef attribute which is the Data Set
	 * associated with the message
	 * @return the dataSetRef
	 */
	public DataSet getDataSetRef() {
		return dataSetRef;
	}   // end Message.getDataSetRef()

	/**
	 * This is a setter message to set the dataSetRef attribute which is the data set
	 * for the message
	 * @param dataSetRef  - the dataSetRef to set
	 */
	public void setDataSetRef(DataSet dataSetRef) {
		this.dataSetRef = dataSetRef;
	}   // end Message.setDataSetRef(DataSet dataSetRef)

	/**
	 * This toString is simply to marshal key information about the class to a string
	 * @return void
	 */
	public String toString() {
		String msgString;
		msgString = "cmnd = " + this.msgType +
				    "\n DataSet = " + this.dataSetRef.getDataSetID() +
				    "\n Instrument = " + this.dataSetRef.getMetaData().getInstrumentID() +
				    "\n Experiment = " + this.dataSetRef.getMetaData().getExperimentID() +
				    "\n Run Set = " + this.dataSetRef.getMetaData().getGroupID() +
				    "\n Run = " + this.dataSetRef.getMetaData().getSequenceNumber();
		
		return msgString;
	}

	/**
	 * This is a setter message to set the dataSetRef attribute which is the data set
	 * for the message
	 * @param dataSetRef - the dataSetRef to set
	 */
	public void setDataSetRef(DataSet dataSetRef) {

	}

}   // end class Message