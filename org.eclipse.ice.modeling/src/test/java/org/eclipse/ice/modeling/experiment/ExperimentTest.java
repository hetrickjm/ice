/**
 * 
 */
package org.eclipse.ice.modeling.experiment;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author 6mq
 *
 */
public class ExperimentTest {
	
	/**
	 * Attributes that hold data to use in the testing
	 */
	private DataSet dataSet;
	private Group group;
	private Sequence seq1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Create a DataSet with MetaData
		// Create the Meta Data for the Data Set that is to be part of the msg
		MetaData meta = new MetaData("INST-1", "EXP-1", "GRP-0", 0);
		meta.setDataType(0);        // set the data type to 0 for the test
		meta.setSequenceTotal(1);   // the total number of expected sequences
		
		// Create the Data Set and with the Meta Data
		this.dataSet = new DataSet(meta, "DS-42");
		this.dataSet.setRawDataRef("INST-1/EXP-1/GRP-0/SEQ-0/DT-0");
		
		// Create a Sequence
		this.seq1 = new Sequence(dataSet);
		
		// Create a Group
		this.group = new Group(dataSet, seq1);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Experiment#Experiment()}.
	 */
	@Test
	public void testExperiment() {
		System.out.println("\n BEGIN TEST: ExperimentTest.testExperiment");
		
		Experiment exp = new Experiment();
		
		// add a group to the Experiment
		exp.addGroup(group);
		
		System.out.println("END TEST: ExperimentTest.testExperiment");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Experiment#Experiment(java.lang.String, java.lang.String, org.eclipse.ice.modeling.experiment.Group)}.
	 */
	@Test
	public void testExperimentStringStringGroup() {
		System.out.println("\n BEGIN TEST: ExperimentTest.testExperimentStringStringGroup");
		
		// Make it easier get the MetaData from the dataSet
		MetaData meta = dataSet.getMetaData();
		Experiment exp = new Experiment(meta.getExperimentID(), meta.getInstrumentID(), this.group);
		
		
		System.out.println("END TEST: ExperimentTest.testExperimentStringStringGroup");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Experiment#getGroups()}.
	 */
	@Test
	public void testGetGroups() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Experiment#addGroup(org.eclipse.ice.modeling.experiment.Group)}.
	 */
	@Test
	public void testAddGroup() {
		System.out.println("\n BEGIN TEST: ExperimentTest.testAddGroup");
		
		Experiment exp = new Experiment();
		
		// add a group to the Experiment
		exp.addGroup(group);
		
		System.out.println("END TEST: ExperimentTest.testAddGroup");
			fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Experiment#getGroup(int)}.
	 */
	@Test
	public void testGetGroup() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Experiment#findGroup(java.lang.String)}.
	 */
	@Test
	public void testFindGroup() {
		System.out.println("\n BEGIN TEST: ExperimentTest.testFindGroup");
		
		// Make it easier get the MetaData from the dataSet
		MetaData meta = dataSet.getMetaData();
		Experiment exp = new Experiment(meta.getExperimentID(), meta.getInstrumentID(), this.group);
		
		// Should find the first Group if there that was created during initialization
		Group grp = exp.findGroup("GRP-0");
		
		// Seach for a group that does not exist.  Should return null
		grp = exp.findGroup("GRP-3");
		
		System.out.println("END TEST: ExperimentTest.testFindGroup");
	}

}
