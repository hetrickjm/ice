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
 * The Data Set is a reference to the data set that is to be or is being reduced (processed).  It holds a link (reference of some kind) to the raw data which is not contained here in.  One of the important things this class holds is the meta data for the data set.
 * 
 * Note: Consider the data set may want to actually have a relationship with the Sequence and/or possibly the Group instead of the Workflow.  The workflow does not need to repeat this information for itself as long as it has a relationship with the entity that holds it.
 * 
 * 
 * @author John Hetrick
 */
public class DataSet {

	/**
	 * Logger for handling event messages and other information.
	 */
	private static final Logger logger = LoggerFactory.getLogger(DataSet.class);
	
	/**
	 * The rawDataRef attribute is a reference or a link to a file that contains the actual 
	 * raw data that needs or is undergoing processing.  This is expected to be a full
	 * path and file as a direct reference to a location on a mounted drive
	 */
	private String rawDataRef;
	
	/**
	 * The metData attribute contains the set of information about the data set.
	 */
	private MetaData metaData;

	/**
	 * The dataSetID attribute is an identifier for the data set
	 */
	private String dataSetID;

	/**
	 * This is the constructor for the DataSet class
	 */
	public DataSet() {
		logger.debug("DataSet() constructor");
		
		metaData   = new MetaData();
		rawDataRef = "Raw Data File Reference";
	}   // end DataSet() constructor

	/**
	 * This is another constructor for the DataSet class that takes
	 * key parameters to initalize the call
	 * @param metaData - The MetaData that belongs to the DataSet
	 * @param dataSetID - A string the uniquely identifies this DataSet
	 */
	public DataSet(MetaData metaData, String dataSetID) {
		logger.debug("DataSet(MetaData metaData, String dataSetID) constructor");
		this.metaData = metaData;
		this.dataSetID = dataSetID;
	}

	/**
	 * This is a getter to return the rawDataRef attribute which is a location
	 * reference to the raw data
	 * 
	 * @return the rawDataRef
	 */
	public String getRawDataRef() {
		return rawDataRef;
	}   // end DataSet.getRawDataRef()

	/**
	 * This is a setter to set the rawDataRef attribute which is a location
	 * reference to the raw data
	 * 
	 * @param rawDataRef the rawDataRef to set
	 */
	public void setRawDataRef(String rawDataRef) {
		this.rawDataRef = rawDataRef;
	}   // end DataSet.setRawDataRef(String rawDataRef)

	/**
	 * This is a getter to return the metaData attribute
	 * @return the metaData
	 */
	public MetaData getMetaData() {
		return metaData;
	}   // end DataSet.getMetaData()

	/**
	 * This is a setter to set the metaData attribute
	 * @param metaData the metaData to set
	 */
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}   // end DataSet.setMetaData(MetaData metaData)

	/**
	 * This is a getter to return the dataSetID attribute 
	 * 
	 * @return the dataSetID
	 */
	public String getDataSetID() {
		return dataSetID;
	}

	/**
	 * This is a setter to set the dataSetID attribute 
	 * 
	 * @param dataSetID the dataSetID to set
	 */
	public void setDataSetID(String setID) {
		this.dataSetID = setID;
	}

}   // end class DataSet