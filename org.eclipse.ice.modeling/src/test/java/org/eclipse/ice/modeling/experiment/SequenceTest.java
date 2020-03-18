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

import org.eclipse.ice.modeling.workflow.*;

/**
 * @author 6mq
 *
 */
public class SequenceTest {

	/**
	 * Attributes that hold data to use in the testing
	 */
	private DataSet dataSet;

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

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Sequence#Sequence()}.
	 */
	@Test
	public void testSequence() {
		System.out.println("\n BEGIN TEST: Sequence.testSequence");
		
		Sequence seq = new Sequence();
		seq.setDataSet(dataSet);
		seq.setSequenceNum(this.dataSet.getMetaData().getSequenceNumber());
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: Sequence.testSequence");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Sequence#Sequence(org.eclipse.ice.modeling.experiment.DataSet)}.
	 */
	@Test
	public void testSequenceDataSet() {
		System.out.println("\n BEGIN TEST: Sequence.testSequenceDataSet");
		
		Sequence seq = new Sequence(this.dataSet);
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: Sequence.testSequenceDataSet");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Sequence#findWorkflow()}.
	 */
	@Test
	public void testFindWorkflow() {
		System.out.println("\n BEGIN TEST: Sequence.testFindWorkflow");
		
		Sequence seq = new Sequence(this.dataSet);
		Workflow wf = seq.findWorkflow();
		
		System.out.println("END TEST: Sequence.testFindWorkflow");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Sequence#findWorkflow(java.lang.String)}.
	 */
	@Test
	public void testFindWorkflowString() {
		System.out.println("\n BEGIN TEST: Sequence.testFindWorkflowString");
		
		Sequence seq = new Sequence(this.dataSet);
		Workflow wf = seq.findWorkflow("WFS-1");
		
		System.out.println("END TEST: Sequence.testFindWorkflowString");
	}

}
