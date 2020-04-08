/**
 * 
 */
package org.eclipse.ice.modeling.workflow;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.eclipse.ice.modeling.experiment.DataSet;
import org.eclipse.ice.modeling.experiment.ExperimentRepo;
import org.eclipse.ice.modeling.experiment.MetaData;
import org.eclipse.ice.modeling.workflowDescription.*;
import org.eclipse.ice.modeling.workflowEngine.Message;

/**
 * @author John Hetrick
 *
 */
public class GroupReduceStitchWFTest {

	/**
	 * A set of attributes that hold data for the test
	 */
	private DataSet dataSet;
	private ExperimentRepo expRepo;
	private IWorkflowDescriptionRepo wfdRepo;
	private IWorkflowDescription wfdg;
	private IWorkflowDescription wfds;
	private Workflow wf;
	
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
		System.out.println("\tBEGIN: GroupReduceStitchWFTest.setUp()");
		// Create a DataSet with MetaData
		// Create the Meta Data for the Data Set that is to be part of the msg
		MetaData meta = new MetaData("INST-1", "EXP-1", "GRP-0", 0);
		meta.setDataType(0);        // set the data type to 0 for the test
		meta.setSequenceTotal(1);   // the total number of expected sequences
		
		// Create the Data Set and with the Meta Data
		this.dataSet = new DataSet(meta, "DS-42");
		this.dataSet.setRawDataRef("INST-1/EXP-1/GRP-0/SEQ-0/DT-0");
		
		// Init a set of repos to work from
		this.initRepos();
		
		// Get the Group WorkflowDescription
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID();
		this.wfdg = this.wfdRepo.findWorkflowDescription(key);
		
		// Get the Sequence WorkflowDescription
		 key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/DT-" + meta.getDataType();
		this.wfds = this.wfdRepo.findWorkflowDescription(key);

		System.out.println("\tEND: GroupReduceStitchWFTest.setUp()");
	}

	/**
	 * Init a set of repos for testing
	 */
	private void initRepos() {
		System.out.println("\tBEGIN: GroupReduceStitchWFTest.initRepos()");
		
		// Create an ExperimentRepo
		this.expRepo = new ExperimentRepo();
		
		// Create a WorkflowDescriptionRepo
		this.wfdRepo = (IWorkflowDescriptionRepo) new WorkflowDescriptionRepo();
		
		System.out.println("\tEND: GroupReduceStitchWFTest.initRepos()");
	}

	/**
	 * Create GroupReduceStitchWF and its childWF for testing
	 */
	private void createGroupWorkflow() {
		System.out.println("\tBEGIN: GroupReduceStitchWFTest.createGroupWorkflow()");
		// Create a new GroupReduceStitchWF to work with a key
		// Note that the GroupReduceStitchWF is being cast into a parent Workflow class
		MetaData meta = this.dataSet.getMetaData();
		int wfCount = 0;
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/WFG-" + wfCount;

		this.wf = new GroupReduceStitchWF(key, this.dataSet, this.wfdg);
		
		// Create a childWorkflow
		createChildWorkflow();
		
		System.out.println("\tEND: GroupReduceStitchWFTest.createGroupWorkflow()");
	}
	
	/*
	 * Create child workflow(s) for testing.  Note child workflows are SeqWorkflows
	 */
	private void createChildWorkflow() {
		System.out.println("\tBEGIN: GroupReduceStitchWFTest.createChildWorkflow()");
		
		// Create a SeqWF key
		// Create a new SeqWF to work with
		MetaData meta = this.dataSet.getMetaData();
		int wfCount = 0;
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/WFS-" + wfCount;

		SeqWF cwf = new SeqWF(key, this.dataSet, this.wfds);
		
		// recast the Workflow to a GroupReduceStitchWF to add the child workflow to the childWorkflowSet
		GroupReduceStitchWF gwf = (GroupReduceStitchWF) this.wf;
		gwf.addChildWorkflow(cwf);
		System.out.println("\tEND: GroupReduceStitchWFTest.createChildWorkflow()");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflow.GroupReduceStitchWF#handleMsg(org.eclipse.ice.modeling.workflowEngine.Message)}.
	 */
	@Test
	public void testHandleMsg() {
		System.out.println("\n BEGIN TEST: GroupReduceStitchWFTest.testHandleMsg");
		
		// Set up some local variables
		Message msgIn = null,
        msgOut = null;
		boolean cmplt = false;
		
		// Create a new GroupReduceStitchWF with its parts
		this.createGroupWorkflow();
		
		msgIn = new Message("POSTPROCESS.DATA_READY");
		msgIn.setDataSetRef(dataSet);
		
		// Start the processing of a sequence by sending in
		// the POSTPROCESS.DATA_READY msg
		msgOut = (Message) this.wf.handleMsg(msgIn);
		
		// Pretend the Reducer is sending in Catalog process status msgs
		this.genCatalogMsgs();
		System.out.println("\tis workflow complete: " + this.wf.isComplete());
		
		// Pretend the Reducer is sending in Reduction process status msgs
		this.genReduceMsgs();
		System.out.println("\tis workflow complete: " + this.wf.isComplete());
		
		// Pretend the Reducer is sending in Reduction Catalog process status msgs
		this.genRedCatMsgs();
		System.out.println("\tis workflow complete: " + this.wf.isComplete());
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: GroupReduceStitchWFTest.testHandleMsg\n");		
	}

	/**
	 * Generate/Send Catalog messages to the workflow
	 */
	private void genCatalogMsgs() {
		System.out.println("\tBEGIN: GroupReduceStitchWFTest.genCatalogMsgs()");
		Message altMsg,
		        msgOut = null;
		boolean cmplt = false;
		
		// Creat the next msgIn in the sequence
		altMsg = new Message ("CATALOG.STARTED");
		altMsg.setDataSetRef(this.dataSet);
		msgOut = (Message) this.wf.handleMsg(altMsg);
		
		// Creat the next msgIn in the sequence
		altMsg = new Message ("CATALOG.COMPLETE");
		altMsg.setDataSetRef(this.dataSet);
		msgOut = (Message) this.wf.handleMsg(altMsg);
		
		//fail("Not yet implemented");
		System.out.println("\tEND: SeqWFTest.genCatalogMsgs()");
	}

	/**
	 * Generate/Send Catalog messages to the workflow
	 */
	private void genReduceMsgs() {
		System.out.println("\tBEGIN: GroupReduceStitchWFTest.genReduceMsgs()");
		Message altMsg,
		        msgOut = null;
		boolean cmplt = false;
		
		// Creat the next msgIn in the sequence
		altMsg = new Message ("REDUCTION.STARTED");
		altMsg.setDataSetRef(this.dataSet);
		msgOut = (Message) this.wf.handleMsg(altMsg);
		
		// Creat the next msgIn in the sequence
		altMsg = new Message ("REDUCTION.COMPLETE");
		altMsg.setDataSetRef(this.dataSet);
		msgOut = (Message) this.wf.handleMsg(altMsg);
		//fail("Not yet implemented");
		System.out.println("\tEND: GroupReduceStitchWFTest.genReduceMsgs()");
	}

	/**
	 * Generate/Send Catalog messages to the workflow
	 */
	private void genRedCatMsgs() {
		System.out.println("\tBEGIN: GroupReduceStitchWFTest.genRedCatMsgs()");
		Message altMsg,
		        msgOut = null;
		boolean cmplt = false;
		
		// Creat the next msgIn in the sequence
		altMsg = new Message ("REDUCTION_CATAGORY.STARTED");
		altMsg.setDataSetRef(this.dataSet);
		msgOut = (Message) this.wf.handleMsg(altMsg);
		
		// Creat the next msgIn in the sequence
		altMsg = new Message ("REDUCTION_CATAGORY.COMPLETE");
		altMsg.setDataSetRef(this.dataSet);
		msgOut = (Message) this.wf.handleMsg(altMsg);
		
		//fail("Not yet implemented");
		System.out.println("\tEND: GroupReduceStitchWFTest.genRedCatMsgs()");
	}

}
