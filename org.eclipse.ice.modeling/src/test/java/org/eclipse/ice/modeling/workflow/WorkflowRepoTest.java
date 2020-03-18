/**
 * 
 */
package org.eclipse.ice.modeling.workflow;

import static org.junit.Assert.*;

import org.eclipse.ice.modeling.experiment.DataSet;
import org.eclipse.ice.modeling.experiment.Group;
import org.eclipse.ice.modeling.experiment.MetaData;
import org.eclipse.ice.modeling.workflowDescription.WorkflowDescription;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author 6mq
 *
 */
public class WorkflowRepoTest {

	/**
	 * A set of attributes that hold data for the test
	 */
	private DataSet dataSet;
	private WorkflowDescription wdg;
	private WorkflowDescription wds;
	
	/**
	 * 
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
		
		// Create the workflow descriptions for use in testing:
		//    - one for a group
		//    - one for a seqeunce (run)
		
		this.wdg = new WorkflowDescription();
		this.wds = new WorkflowDescription();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflow.WorkflowRepo#WorkflowRepo()}.
	 */
	@Test
	public void testWorkflowRepo() {
		System.out.println("\n BEGIN TEST: WorkflowRepo.testWorkflowRepo");
		
		WorkflowRepo wfr = new WorkflowRepo();
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: WorkflowRepo.testWorkflowRepo");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflow.WorkflowRepo#findWorkflow(org.eclipse.ice.modeling.experiment.DataSet, org.eclipse.ice.modeling.workflowDescription.WorkflowDescription)}.
	 */
	@Test
	public void testFindWorkflow() {
		System.out.println("\n BEGIN TEST: WorkflowRepo.testWorkflowRepo");
		
		WorkflowRepo wfr = new WorkflowRepo();
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: WorkflowRepo.testWorkflowRepo");
	}

}
