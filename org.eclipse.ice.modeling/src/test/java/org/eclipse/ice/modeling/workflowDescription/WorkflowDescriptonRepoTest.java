/**
 * 
 */
package org.eclipse.ice.modeling.workflowDescription;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.eclipse.ice.modeling.experiment.*;

/**
 * @author 6mq
 *
 */
public class WorkflowDescriptonRepoTest {

	private WorkflowDescriptonRepo wdr = new WorkflowDescriptonRepo();
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
		meta.setDataType(0);   // set the data type to 0 for the test
		
		// Create the Data Set and with the Meta Data
		this.dataSet = new DataSet(meta, "DS-42");

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptonRepo#WorkflowDescriptonRepo()}.
	 */
	@Test
	public void testWorkflowDescriptonRepo() {
		System.out.println("TEST START: ");
		
		this.wdr = new WorkflowDescriptonRepo();
		
		System.out.println("TEST FINISHED: " + wdr + "\n");
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptonRepo#getWorkflowDescription(org.eclipse.ice.modeling.experiment.MetaData)}.
	 */
	@Test
	public void testGetWorkflowDescription() {
		System.out.println("TEST START: getWorkflowDescription");
		
		WorkflowDescription wd = wdr.getWorkflowDescription(this.dataSet.getMetaData());
		
		System.out.println("TEST FINISHED: " + wd + "\n");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptonRepo#addWorkflowDescription(org.eclipse.ice.modeling.workflowDescription.WorkflowDescription)}.
	 */
	@Test
	public void testAddWorkflowDescription() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptonRepo#findWorkflowDescription(org.eclipse.ice.modeling.experiment.MetaData)}.
	 */
	@Test
	public void testFindWorkflowDescription() {
		System.out.println("TEST START: FindWorkflowDescription");
		
		WorkflowDescription wd = wdr.getWorkflowDescription(this.dataSet.getMetaData());
		
		System.out.println("TEST FINISHED: " + wd + "\n");
	}

}
