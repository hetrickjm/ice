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
package org.eclipse.ice.modeling.experiment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Meta Data class contains all the pertinent information the workflow system needs to determine which workflow activity to use to reduce (process) the data set to which the meta data belongs.
 * 
 * @author John Hetrick
 */
public class MetaData {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(MetaData.class);
	
	/**
	 * This attribute is identifies the facility that generated the data set
	 */
	private String facilityID;

	/**
	 * This attribute is an identifier for the instrument that produced the data set.
	 */
	private String instrumentID;

	/**
	 * This attribute is an identifier for the experiment that the data set is associated with.
	 */
	private String experimentID;
	
	/**
	 * This attribute is an identifier for the run that represents the data set.
	 */
	private int sequenceNumber;
	
	/**
	 * This attribute is an identifier for the run set (group) that the data set is associated with.
	 */
	private String groupID;
	/**
	 * This attribute is the data type of the data set.  It is likely to be value between 0 - 5.  The meaning of the values are unique to each instrument
	 */
	private int dataType;
	/**
	 * The sequenceTotal is the total number of expected sequences in the group
	 */
	private int sequenceTotal;
	
	/**
	 * This is the constructor for the MetaData class
	 */
	public MetaData() {
		logger.debug("MetaData() constructor");
		
		this.setInstrumentID("instrumentID");
		this.setExperimentID("experimentID");
		this.setSequenceNumber(0);
	}

	/**
	 * This is another contructor for the MetaData Class to set the attributes
	 * at the time of construction.  Currently this is seen as a convenient constructor
	 * for testing and may go away in the furture
	 * @param instID - An ID for the instrumentID attribute
	 * @param expID - An ID for the experimentID attribute
	 * @param groupID - An ID for the groupID attribute
	 * @param seqNum - An ID for the sequenceNumber attribute
	 */
	public MetaData(String instID, String expID, String grpID, int seqNum) {
		// super();   // Should super be called?
		this.instrumentID   = instID;
		this.experimentID   = expID;
		this.groupID        = grpID;
		this.sequenceNumber = seqNum;
	}

	/**
	 * This is a getter to return the facilityID attribute
	 */
	public String getFacilityID() {
		return this.facilityID;
	}

	/**
	 * This is a setter to set the facilityID attribute
	 * @param id  - the ID to use in setting the facilityID attribute
	 */
	public void setFacilityID(String id) {
		this.facilityID = id;
	}

	/**
	 * This is a getter to return the instrumentID attribute
	 * @return the instrumentID
	 */
	public String getInstrumentID() {
		return instrumentID;
	}

	/**
	 * This is a setter to set the instrumentID attribute
	 * @param id  - the ID to use to set the instrumentID attribute
	 */
	public void setInstrumentID(String id) {
		this.instrumentID = instrumentID;
	}

	/**
	 * This is a getter to return the experimentID attribute
	 * @return the experimentID
	 */
	public String getExperimentID() {
		return experimentID;
	}

	/**
	 * This is a setter to set the ExperimentID attribute
	 * @param id  - the ID to use to set the experimentID attribute
	 */
	public void setExperimentID(String id) {
		this.experimentID = experimentID;
	}

	/**
	 * This is a getter to return the sequenceNumber attribute
	 * @return the runID
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * This is a setter to set the sequenceNumber attribute
	 * @param id - the number used to set the sequenceNumber attribute
	 */
	public void setSequenceNumber(int id) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * This is a getter to return the groupID attribute
	 * @return the runSetID
	 */
	public String getGroupID() {
		return this.groupID;
	}

	/**
	 * This is a setter to set the groupID attribute
	 * @param id - ID to use in setting the groupID attribute
	 */
	public void setGroupID(String id) {
		this.groupID = id;
	}

	/**
	 * This is a getter to return the dataType attribute
	 */
	public int getDataType() {
		return this.dataType;
	}

	/**
	 * This is a setter method to set the dataType attribute
	 * @param type  - type is the value to use for setting the dataType
	 */
	public void setDataType(int type) {
		this.dataType = type;
	}

	/**
	 * This is a getter to return the sequenceTotal attribute
	 * @return total number of expected sequences (runs)
	 */
	public int getSequenceTotal() {
		return this.sequenceTotal;
	}

	/**
	 * This is a setter to sey the sequenceTotal attribute
	 * @param total - the number of expected sequences (runs)
	 */
	public void setSequenceTotal(int total) {
		this.sequenceTotal = total;
	}

}   // end MetaData class