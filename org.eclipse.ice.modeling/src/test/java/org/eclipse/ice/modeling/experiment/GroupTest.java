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
public class GroupTest {
	/**
	 * A set of attributes that hold data for the test
	 */
	private DataSet dataSet;
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

		this.seq1 = new Sequence(dataSet);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Group#Group()}.
	 */
	@Test
	public void testGroup() {
		System.out.println("\n BEGIN TEST: Group.testGroup");
		
		Group group = new Group();
		group.setDataSet(dataSet);
		group.addSeq(seq1);
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: Group.testGroup");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Group#Group(org.eclipse.ice.modeling.experiment.DataSet, org.eclipse.ice.modeling.experiment.Sequence)}.
	 */
	@Test
	public void testGroupDataSetSequence() {
		System.out.println("\n BEGIN TEST: Group.testGroupDataSetSequence");
		
		Group group = new Group(this.dataSet, this.seq1);
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: Group.testGroupDataSetSequence");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Group#findSequence(int)}.
	 */
	@Test
	public void testFindSequence() {
		System.out.println("\n BEGIN TEST: Group.testFindSequence");
		
		Group group = new Group(this.dataSet, this.seq1);
		Sequence seq = group.findSequence(0);    // This currently only test for the first sequence
		seq = group.findSequence(3);             // This should return null
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: Group.testFindSequence");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Group#findWorkflow()}.
	 */
	@Test
	public void testFindWorkflow() {
		System.out.println("\n BEGIN TEST: Group.testFindWorkflow");
		
		Group group = new Group(this.dataSet, this.seq1);
		
		Workflow wf = group.findWorkflow();
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: Group.testFindWorkflow");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.experiment.Group#findWorkflow(java.lang.String)}.
	 */
	@Test
	public void testFindWorkflowString() {
		System.out.println("\n BEGIN TEST: Group.testFindWorkflowString");
		
		Group group = new Group(this.dataSet, this.seq1);
		
		Workflow wf = group.findWorkflow("WFG-1");
		
		System.out.println("END TEST: Group.testFindWorkflowString");
	}

}
