/**
 * 
 */
package org.eclipse.ice.modeling.workflowDescription.tasks;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.eclipse.ice.modeling.experiment.DataSet;
import org.eclipse.ice.modeling.experiment.MetaData;
import org.eclipse.ice.modeling.workflow.TaskStatus;
import org.eclipse.ice.modeling.workflowDescription.CompletionCriteria;
import org.eclipse.ice.modeling.workflowDescription.Criteria;
import org.eclipse.ice.modeling.workflowDescription.StringCriteria;
import org.eclipse.ice.modeling.workflowEngine.Message;
import org.eclipse.ice.modeling.workflowDescription.action.ActionMsg;

/**
 * @author 6mq
 *
 */
public class SeqRedCatTaskTest {

	/**
	 * Attributes that hold data to use in the testing
	 */
	private TaskStatus taskStat = null;
	private Task task = null;
	private Message msgIn = null;
	private DataSet dataSet = null;

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
		System.out.println("\n BEGIN SETUP: SeqRedCatTaskTest.setUp");
		// Create a DataSet with MetaData
		// Create the Meta Data for the Data Set that is to be part of the msg
		MetaData meta = new MetaData("INST-1", "EXP-1", "GRP-0", 0);
		meta.setDataType(0);        // set the data type to 0 for the test
		meta.setSequenceTotal(1);   // the total number of expected sequences
		
		// Create the Data Set and with the Meta Data
		this.dataSet = new DataSet(meta, "DS-42");
		this.dataSet.setRawDataRef("INST-1/EXP-1/GRP-0/SEQ-0/DT-0");
		
		// create an action
		Message msg   = new Message("REDUCTION_CATALOG.DATA_READY");
		ActionMsg actMsg = new ActionMsg(msg);
		// Create and set the completion criteria for this task
		Criteria startCrit = new StringCriteria("REDUCTION_CATALOG.STARTED");
		Criteria cmpltCrit = new StringCriteria("REDUCTION_CATALOG.COMPLETE");
		CompletionCriteria complete = new CompletionCriteria();
		complete.addCompletionCreiteria(startCrit);
		complete.addCompletionCreiteria(cmpltCrit);

		// create a task
		this.task = new SeqReduceTask("TSK-1", actMsg);
		task.setCompletionCriteria(complete);
		
		// create a TaskStatus
		this.taskStat = new TaskStatus(this.task);
		
		// create an in message
		this.msgIn = new Message("POSTPROCESS.DATA_READY");
		this.msgIn.setDataSetRef(dataSet);

		System.out.println("END SETUP: SeqRedCatTaskTest.setUp\n");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.tasks.SeqRedCatTask#execute()}.
	 */
	@Test
	public void testExecute() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.tasks.SeqRedCatTask#execute(java.lang.Object)}.
	 */
	@Test
	public void testExecuteObject() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.tasks.SeqRedCatTask#execute(org.eclipse.ice.modeling.workflow.Workflow, java.lang.Object)}.
	 */
	@Test
	public void testExecuteWorkflowObject() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eclipse.ice.modeling.workflowDescription.tasks.SeqRedCatTask#execute(org.eclipse.ice.modeling.workflow.TaskStatus, java.lang.Object)}.
	 */
	@Test
	public void testExecuteTaskStatusObject() {
		System.out.println("\n BEGIN TEST: SeqRedCatTaskTest.testExecuteTaskStatusObject");
		Message altMsg,
		        msgOut = null;
		boolean cmplt = false;
		
		// Send multiple messages simulating a set to complete a task
		msgOut = (Message) this.task.execute(this.taskStat, this.msgIn);
		
		// Check if task complete
		cmplt = this.taskStat.isComplete();
		System.out.println("\tis Task Complete: " + cmplt);
		
		// Creat the next msgIn in the sequence
		altMsg = new Message ("REDUCTION_CATALOG.STARTED");
		altMsg.setDataSetRef(this.dataSet);
		msgOut = (Message) this.task.execute(this.taskStat, altMsg);
		
		// Check if task complete
		cmplt = this.taskStat.isComplete();
		System.out.println("\tis Task Complete: " + cmplt);
		
		// Creat the next msgIn in the sequence
		altMsg = new Message ("REDUCTION_CATALOG.COMPLETE");
		altMsg.setDataSetRef(this.dataSet);
		msgOut = (Message) this.task.execute(this.taskStat, altMsg);
		
		// Check if task complete
		cmplt = this.taskStat.isComplete();
		System.out.println("\tis Task Complete: " + cmplt);
		
		//fail("Not yet implemented"); // TODO
		System.out.println("END TEST: SeqRedCatTaskTest.testExecuteTaskStatusObject\n");
	}

}
