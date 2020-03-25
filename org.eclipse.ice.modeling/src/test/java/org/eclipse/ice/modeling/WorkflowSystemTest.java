/**
 * 
 */
package org.eclipse.ice.modeling;

import static org.junit.Assert.*;

import org.eclipse.ice.modeling.actors.ReducerStub;
import org.eclipse.ice.modeling.actors.TransSrvcStub;
import org.eclipse.ice.modeling.experiment.DataSet;
import org.eclipse.ice.modeling.experiment.ExperimentRepo;
import org.eclipse.ice.modeling.experiment.MetaData;
import org.eclipse.ice.modeling.workflow.WorkflowRepo;
import org.eclipse.ice.modeling.workflowDescription.WorkflowDescriptionRepo;
import org.eclipse.ice.modeling.workflowEngine.Message;
import org.eclipse.ice.modeling.workflowEngine.WorkflowEngine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author 6mq
 *
 */
public class WorkflowSystemTest {

	/**
	 * A set of attributes that hold data for the test
	 */
	private TransSrvcStub transSrvc;
	private DataSet dataSet;
	private ReducerStub reducer;
	private WorkflowEngine wfEng;
	private WorkflowDescriptionRepo wfdRepo; 
	private WorkflowRepo wfRepo;
	private ExperimentRepo expRepo;

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
		this.wfdRepo.setExpRepo(this.expRepo);
		
		// Create a WorkflowRepo
		this.wfRepo = new WorkflowRepo();
		this.wfRepo.setExpRepo(expRepo);
		this.wfRepo.setWorkflowDescriptionRepo(this.wfdRepo);
		
		// Create a TransitionService
		this.transSrvc = new TransSrvcStub();
		
		// Create a Reducer
		this.reducer = new ReducerStub();
		
		this.wfEng = new WorkflowEngine( this.reducer, this.wfRepo, this.wfdRepo);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.WorkflowSystem#WorkflowSystem(org.eclipse.ice.modeling.actors.TransSrvcStub, org.eclipse.ice.modeling.actors.ReducerStub)}.
	 */
	@Test
	public void testWorkflowSystemTransSrvcStubReducerStub() {
		System.out.println("\n BEGIN TEST: WorkflowSystem.testWorkflowSystemTransSrvcStubReducerStub");
		
		//WorkflowSystem wfs = new WorkflowSystem(this.transSrvc, this.reducer);
		
		System.out.println("END TEST: WorkflowSystem.testWorkflowSystemTransSrvcStubReducerStub\n");		
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.WorkflowSystem#initSystem()}.
	 */
	@Test
	public void testInitSystem() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.WorkflowSystem#getWorkflowEngine()}.
	 */
	@Test
	public void testGetWorkflowEngine() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.WorkflowSystem#initWorkflowRepo()}.
	 */
	@Test
	public void testInitWorkflowRepo() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.WorkflowSystem#initWorkflowDescriptionRepo()}.
	 */
	@Test
	public void testInitWorkflowDescriptionRepo() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.WorkflowSystem#handleMsg(org.eclipse.ice.modeling.workflowEngine.Message)}.
	 */
	@Test
	public void testHandleMsg() {
		System.out.println("\n BEGIN TEST: WorkflowSystem.testHandleMsg");
		// instantiate the workflow system
		WorkflowSystem wfs = new WorkflowSystem(this.transSrvc, this.reducer);
		
		// Create a Message and send it to the Workflow
		Message msg = new Message();
		msg.setMsgType("POSTPROCESS.DATA_READY");
		
		// Add the Data Set to the message
		msg.setDataSetRef(dataSet);

		System.out.println("   " + msg.toString());
		
		this.wfEng.handleMsg(msg);
		
		System.out.println("END TEST: WorkflowSystem.testHandleMsg\n");		
	}

}
