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
import org.eclipse.ice.modeling.workflow.Workflow;

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
		System.out.println("\n BEGIN TEST: WorkflowDescriptionRepo.testWorkflowDescriptonRepo");
		
		this.wdr = new WorkflowDescriptonRepo();
		
		System.out.println("END TEST: WorkflowDescriptionRepo.testWorkflowDescriptonRepo");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptonRepo#getWorkflowDescription(org.eclipse.ice.modeling.experiment.MetaData)}.
	 */
	@Test
	public void testGetWorkflowDescription() {
		System.out.println("\n BEGIN TEST: WorkflowDescriptionRepo.testGetWorkflowDescription");
		
		WorkflowDescription wd = wdr.getWorkflowDescription(this.dataSet.getMetaData());
		
		System.out.println("END TEST: WorkflowDescriptionRepo.testGetWorkflowDescription");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptonRepo#addWorkflowDescription(org.eclipse.ice.modeling.workflowDescription.WorkflowDescription)}.
	 */
	@Test
	public void testAddWorkflowDescription() {
		System.out.println("\n BEGIN TEST: WorkflowDescriptionRepo.testFindWorkflowDescription");
		
		fail("Not yet implemented"); // TODO
		
		System.out.println("END TEST: WorkflowDescriptionRepo.testFindWorkflowString");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptonRepo#findWorkflowDescription(org.eclipse.ice.modeling.experiment.MetaData)}.
	 */
	@Test
	public void testFindWorkflowDescription() {
		System.out.println("\n BEGIN TEST: WorkflowDescriptionRepo.testFindWorkflowDescription");
		
		WorkflowDescription wd = wdr.findWorkflowDescription(this.dataSet.getMetaData());
		
		System.out.println("END TEST: WorkflowDescriptionRepo.testFindWorkflowDescription");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptonRepo#findWorkflowDescription(java.lang.String)}.
	 */
	@Test
	public void testFindWorkflowDescriptionString() {
		System.out.println("\n BEGIN TEST: WorkflowDescriptionRepo.testFindWorkflowDescriptionString");
		
		MetaData meta = dataSet.getMetaData();
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID();
		WorkflowDescription wd = wdr.findWorkflowDescription(key);
		
		System.out.println("END TEST: WorkflowDescriptionRepo.testFindWorkflowString");
	}

}
