/**
 * 
 */
package org.eclipse.ice.modeling.workflow;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ice.modeling.experiment.DataSet;
import org.eclipse.ice.modeling.experiment.ExperimentRepo;
import org.eclipse.ice.modeling.experiment.MetaData;
import org.eclipse.ice.modeling.workflowDescription.CompletionCriteria;
import org.eclipse.ice.modeling.workflowDescription.Criteria;
import org.eclipse.ice.modeling.workflowDescription.StringCriteria;
import org.eclipse.ice.modeling.workflowDescription.*;
import org.eclipse.ice.modeling.workflowDescription.tasks.*;
import org.eclipse.ice.modeling.workflowDescription.action.*;
import org.eclipse.ice.modeling.workflowEngine.Message;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author 6mq
 *
 */
public class SeqWFTest {

	/**
	 * A set of attributes that hold data for the test
	 */
	private DataSet dataSet;
	private ExperimentRepo expRepo;
	private IWorkflowDescriptionRepo wfdRepo;
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
		// Create a DataSet with MetaData
		// Create the Meta Data for the Data Set that is to be part of the msg
		MetaData meta = new MetaData("INST-1", "EXP-1", "GRP-0", 0);
		meta.setDataType(0);        // set the data type to 0 for the test
		meta.setSequenceTotal(1);   // the total number of expected sequences
		
		// Create the Data Set and with the Meta Data
		this.dataSet = new DataSet(meta, "DS-42");
		this.dataSet.setRawDataRef("INST-1/EXP-1/GRP-0/SEQ-0/DT-0");
		
		// Create an Repos
		this.initRepos();
		
		// Get the Sequence WorkflowDescription
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/DT-" + meta.getDataType();
		this.wfds = this.wfdRepo.findWorkflowDescription(key);

	}

	/**
	 * Init a set of repos for testing
	 */
	private void initRepos() {
		System.out.println("\tBEGIN: SeqWFTest.initRepos()");
		
		// Create an ExperimentRepo
		this.expRepo = new ExperimentRepo();
		
		// Create a WorkflowDescriptionRepo
		this.wfdRepo = new WorkflowDescriptionRepo();
		
		System.out.println("\tEND: SeqWFTest.initRepos()");
	}

	/*
	 * Create Sequence workflow(s) for testing.
	 */
	private void createSeqWorkflow() {
		System.out.println("\tBEGIN: SeqWFTest.createSeqWorkflow()");
		
		// Create a SeqWF key
		// Create a new SeqWF to work with
		MetaData meta = this.dataSet.getMetaData();
		int wfCount = 0;
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/WFS-" + wfCount;

		this.wf = new SeqWF(key, this.dataSet, this.wfds);
		
		// recast the Workflow to a GroupWF to add the child workflow to the childWorkflowSet
		//GroupWF gwf = (GroupWF) this.wf;
		//gwf.addChildWorkflow(cwf);
		System.out.println("\tEND: SeqWFTest.createChildWorkflow()");
	}

	/**
	 * THIS METHOD IS FOR EXPLORING THE WORKFLOW CONCEPTS AND MAY BE CHANGED OR DEPRECATED
	 * This method was taken from WorkflowDescriptionRepo class
	 * 
	 * This method creates a canned WorkflowDescription for a Sequence.
	 */
	private WorkflowDescription testCreateSeqWFD() {
		System.out.println("WorkflowDescriptionRepo.testCreateSeqWFD()");
		// Init some variables
		int index;
		List <Task> taskSet = new ArrayList <Task>();
		WorkflowDescription wfd = new WorkflowDescription("WDT-0", WorkflowDescriptionType.SEQ);
		
		System.out.println("\tCreating Seq Task 1");
		// Create the 3 MsgActions to be used in Tasks
		// These represent the 3 messages assoicated with getting a DataSet reduced
		// NOTE:  THIS IS ONLY FOR EXPLORATION
		Message msgCat   = new Message("CATALOG.DATA_READY");
		ActionMsg actCat = new ActionMsg(msgCat);
		taskSet.add(new SeqCatalogTask("TSK-0", actCat));                // Add a new Task
		index = taskSet.size() - 1;
		
		// Create and set the completion criteria for this task
		Criteria startCrit1 = new StringCriteria("CATALOG.STARTED");
		Criteria cmpltCrit1 = new StringCriteria("CATALOG.COMPLETE");
		CompletionCriteria complete1 = new CompletionCriteria();
		complete1.addCompletionCreiteria(startCrit1);
		complete1.addCompletionCreiteria(cmpltCrit1);
		
		taskSet.get(index).setCompletionCriteria(complete1);        // Set the completion criteria
		wfd.addTask(taskSet.get(index));                            // Add the task to the workflowDescription
		
		/////////////////////////////////////////////////////////
		System.out.println("\tCreating Seq Task 2");
		// Add a second task
		Message msgRed   = new Message("REDUCTION.DATA_READY");
		ActionMsg actRed = new ActionMsg(msgRed);
		taskSet.add(new SeqReduceTask("TSK-1", actRed));
		index = taskSet.size() - 1;
		
		// Create and set the completion criteria for this task
		Criteria startCrit2 = new StringCriteria("REDUCTION.STARTED");
		Criteria cmpltCrit2 = new StringCriteria("REDUCTION.COMPLETE");
		CompletionCriteria complete2 = new CompletionCriteria();
		complete2.addCompletionCreiteria(startCrit2);
		complete2.addCompletionCreiteria(cmpltCrit2);
		
		taskSet.get(index).setCompletionCriteria(complete2);        // Set the completion criteria
		wfd.addTask(taskSet.get(index));                            // Add the task to the workflowDescription
		
		/////////////////////////////////////////////////////////
		System.out.println("\tCreating Seq Task 3");
		// Add a Third task
		Message msgRedCat   = new Message("REDUCTION_CATAGORY.DATA_READY");
		ActionMsg actRedCat = new ActionMsg(msgRedCat);
		taskSet.add(new SeqRedCatTask("TSK-2", actRedCat));
		index = taskSet.size() - 1;
		
		// Create and set the completion criteria for this task
		Criteria startCrit3 = new StringCriteria("REDUCTION_CATAGORY.STARTED");
		Criteria cmpltCrit3 = new StringCriteria("REDUCTION_CATAGORY.COMPLETE");
		CompletionCriteria complete3 = new CompletionCriteria();
		complete3.addCompletionCreiteria(startCrit3);
		complete3.addCompletionCreiteria(cmpltCrit3);
		
		taskSet.get(index).setCompletionCriteria(complete3);        // Set the completion criteria
		wfd.addTask(taskSet.get(index));                            // Add the task to the workflowDescription
		
		// Add WorkflowDescription CompletionCriteria
		Criteria taskCrit = new StringCriteria("TASKS.COMPLETE");
		CompletionCriteria taskComplete = new CompletionCriteria();
		taskComplete.addCompletionCreiteria(taskCrit);
		wfd.setCompleteCriteria(taskComplete);
		
		return wfd;
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflow.SeqWF#handleMsg(org.eclipse.ice.modeling.workflowEngine.Message)}.
	 */
	@Test
	public void testHandleMsg() {
		System.out.println("\n BEGIN TEST: SeqWF.testHandleMsg");
		
		// Set up some local variables
		Message msgIn = null,
        msgOut = null;
		boolean cmplt = false;
		
		/**
		 * 
		// Create a new SeqWF to work with
		MetaData meta = this.dataSet.getMetaData();
		int wfCount = 0;
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/WFS-" + wfCount;

		this.wf = new SeqWF(key, this.dataSet, this.wfds);
		 */
		
		// Create a new GroupWF with its parts
		this.createSeqWorkflow();
		
		msgIn = new Message("POSTPROCESS.DATA_READY");
		msgIn.setDataSetRef(dataSet);
		
		// Start the processing of a sequence by sending in
		// the POSTPROCESS.DATA_READY msg
		msgOut = (Message) this.wf.handleMsg(msgIn);
		
		// Pretending the in message is to start the reduction of a Sequence
		this.genCatalogMsgs();
		System.out.println("\tis workflow complete: " + this.wf.isComplete());
		
		
		// This has been stimulated by the last set of message to be processed, so this msgIn should
		// not contain a msg
		this.genReduceMsgs();
		System.out.println("\tis workflow complete: " + this.wf.isComplete());
		
		// This has been stimulated by the last set of message to be processed, so this msgIn should
		// not contain a msg
		this.genRedCatMsgs();
		System.out.println("\tis workflow complete: " + this.wf.isComplete());
		
		System.out.println("END TEST: SeqWF.testHandleMsg");		
	}

	/**
	 * Generate/Send Catalog messages to the workflow
	 */
	private void genCatalogMsgs() {
		System.out.println("\tBEGIN: SeqWFTest.genCatalogMsgs()");
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
		System.out.println("\tBEGIN: SeqWFTest.genReduceMsgs()");
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
		System.out.println("\tEND: SeqWFTest.genReduceMsgs()");
	}

	/**
	 * Generate/Send Catalog messages to the workflow
	 */
	private void genRedCatMsgs() {
		System.out.println("\tBEGIN: SeqWFTest.genRedCatMsgs()");
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
		System.out.println("\tEND: SeqWFTest.genRedCatMsgs()");
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflow.SeqWF#SeqWF(java.lang.String, org.eclipse.ice.modeling.experiment.DataSet, org.eclipse.ice.modeling.workflowDescription.WorkflowDescription)}.
	 */
	@Test
	public void testSeqWFStringDataSetWorkflowDescription() {
		System.out.println("\n BEGIN TEST: SeqWF.testSeqWFStringDataSetWorkflowDescription");
		
		MetaData meta = this.dataSet.getMetaData();
		int wfCount = 0;
		String key = meta.getInstrumentID() +
				"/" + meta.getExperimentID() +
				"/" + meta.getGroupID() +
				"/WFS-" + wfCount;

		SeqWF wfs = new SeqWF(key, this.dataSet, this.wfds);
		
		System.out.println("END TEST: SeqWF.testSeqWFStringDataSetWorkflowDescription");		
	}

}
