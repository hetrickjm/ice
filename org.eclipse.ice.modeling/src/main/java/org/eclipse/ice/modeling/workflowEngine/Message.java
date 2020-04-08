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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.ice.modeling.experiment.*;
import org.eclipse.ice.modeling.workflow.SeqWF;

/**
 * The Message class represents any message that has been received from an actor
 * or needs to be sent to an actor.  It is not clear at this point if there needs
 * to be specializations of Message or not, but I suspect there will.
 * 
 * @author John Hetrick
 */
public class Message {
	
	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(Message.class);
	
	/**
	 * The msgType attribute represents a command or request that specifies what 
	 * the message is about.  Note: This will likely change as protocols are 
	 * identified and real messages are specified.  For now it serves for testing 
	 * concepts
	 */
	private String msgType;
	
	/**
	 * Reference to a data set
	 */
	private DataSet dataSetRef = null;

	/**
	 * This is the constructor for the Message class
	 */
	public Message() {
		logger.debug("Message() constructor");
		
		// Init attributes
		this.setMsgType("blank");
	}
	
	/**
	 * This is another constructor for the Message class.  It takes a message type as a parameter.
	 * 
	 * @param type - msg type to set the the msgType attribute
	 */
	public Message(String msg) {
		logger.debug("Message(String msg) constructor");
		
		// Init the attributes
		this.setMsgType(msg);
	}

	/**
	 * This is another constructor for the Message class.  It takes a Message as a
	 * parameter.  This method effectively makes a copy of the passed in message.
	 * Note the DataSet is NOT copied
	 * 
	 * @param msg - the Message to be copied into a new message
	 */
	public Message(Message msg) {
		logger.debug("Message(Message msg) constructor");
		this.msgType = msg.getMsgType();
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
				    " DataSet = " + this.dataSetRef.getDataSetID() +
				    " Instrument = " + this.dataSetRef.getMetaData().getInstrumentID() +
				    " Experiment = " + this.dataSetRef.getMetaData().getExperimentID() +
				    " Run Set = " + this.dataSetRef.getMetaData().getGroupID() +
				    " Run = " + this.dataSetRef.getMetaData().getSequenceNumber();
		
		return msgString;
	}

}   // end class Message