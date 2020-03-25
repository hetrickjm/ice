/**
 * 
 */
package org.eclipse.ice.modeling.workflow;

import static org.junit.Assert.*;

import org.eclipse.ice.modeling.experiment.DataSet;
import org.eclipse.ice.modeling.experiment.MetaData;
import org.eclipse.ice.modeling.experiment.Sequence;
import org.eclipse.ice.modeling.experiment.ExperimentRepo;
import org.eclipse.ice.modeling.workflowDescription.WorkflowDescription;
import org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptionRepo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author 6mq
 *
 */
public class GroupWFTest {
	
	/**
	 * A set of attributes that hold data for the test
	 */
	private DataSet dataSet;
	private ExperimentRepo expRepo;
	private WorkflowDescriptionRepo wfdRepo;
	private WorkflowDescription wfd;

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
		
		// Create an ExperimentRepo
		this.expRepo = new ExperimentRepo();
		
		// Create a WorkflowDescriptionRepo
		this.wfdRepo = new WorkflowDescriptionRepo();
		
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID();
		this.wfd = this.wfdRepo.findWorkflowDescription(key);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflow.GroupWF#handleMsg(org.eclipse.ice.modeling.workflowEngine.Message)}.
	 */
	@Test
	public void testHandleMsg() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflow.GroupWF#GroupWF(java.lang.String, org.eclipse.ice.modeling.experiment.DataSet, org.eclipse.ice.modeling.workflowDescription.WorkflowDescription)}.
	 */
	@Test
	public void testGroupWFStringDataSetWorkflowDescription() {
		System.out.println("\n BEGIN TEST: GroupWF.testGroupWFStringDataSetWorkflowDescription");
		
		MetaData meta = this.dataSet.getMetaData();
		int wfCount = 0;
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/WFG-" + wfCount;

		GroupWF wf = new GroupWF(key, this.dataSet, this.wfd);
		
		System.out.println("END TEST: GroupWF.testGroupWFStringDataSetWorkflowDescription");		
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflow.GroupWF#getChildWorkflowSet(java.lang.String)}.
	 */
	@Test
	public void testGetChildWorkflowSetString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflow.GroupWF#addChildWorkflow(org.eclipse.ice.modeling.workflow.Workflow)}.
	 */
	@Test
	public void testAddChildWorkflow() {
		fail("Not yet implemented"); // TODO
	}

}
