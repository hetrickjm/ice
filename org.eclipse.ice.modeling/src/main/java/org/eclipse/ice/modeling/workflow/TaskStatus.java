package org.eclipse.ice.modeling.workflow;

import java.util.*;

import org.eclipse.ice.modeling.workflowDescription.*;
import org.eclipse.ice.modeling.workflowDescription.tasks.*;
import org.eclipse.ice.modeling.states.*;

/**
 * The TaskStatus class contains the current status of a Task in a workflow.
 * 
 * @author John Hetrick
 */
public class TaskStatus {

	/**
	 * The task attribute is a pointer to a task that is part of the workflow
	 */
	private Task task;
	
	/**
	 * The completionCriteria attribute holds the completion criteria for the Task
	 */
	private List <Criteria> criteria;
	
	/**
	 * The state attribute holds current state of the Task
	 */
	private WorkflowState state;

	/**
	 * This is the constructor for the TaskStatus class
	 */
	public TaskStatus() {
		System.out.println("TaskStatus() constructor");
		
		this.criteria = new ArrayList <Criteria>();
		this.state = WorkflowState.NOT_STARTED;
	}

	/**
	 * This is another constructor for the TaskStatus class
	 * @param tsk - the Task to set the task attribute
	 */
	public TaskStatus(Task tsk) {
		System.out.println("TaskStatus(Task tsk) constructor");
		
		this.task = tsk;
		this.criteria = new ArrayList <Criteria>();
		this.state = WorkflowState.NOT_STARTED;
	}

	/**
	 * This is a getter method to return the task attribute.  Ideally this is a pointer to a Task
	 */
	public Task getTask() {
		System.out.println("TaskStatus.getTask()");
		return this.task;
	}

	/**
	 * This is a setter method to set the task attribute.
	 * @param tsk - the Task pointer to use to set the task attribute
	 */
	public void setTask(Task tsk) {
		System.out.println("TaskStatus.getTask()");
		this.task = tsk;
	}

	/**
	 * This is a getter method to return the state attribute
	 */
	public WorkflowState getState() {
		System.out.println("TaskStatus.getState()");
		return this.state;
	}

	/**
	 * This is a setter method to set the state attribute
	 * @param status - the WorkflowSate value to set the state attribute to
	 */
	public void setState(WorkflowState status) {
		System.out.println("TaskStatus.setState(WorkflowState status)");
		this.state = status;
	}

	/**
	 * @return the criteria
	 */
	public List<Criteria> getCriteria() {
		System.out.println("TaskStatus.getCriteria()");
		return criteria;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void addCriteria(Criteria criterion) {
		System.out.println("TaskStatus.addCriteria(Criteria criterion)");
		this.criteria.add(criterion);
	}

	/**
	 * This method checks to see if the Task is complete.  This is done by comparing the
	 * completionCriteria to the Tasks completionCriteria
	 */
	public boolean isComplete() {
		System.out.println("TaskStatus.isComplete()");
		
		// Compare the criteria attribute with the tasks completion criteria
		return this.task.isTaskComplete(this.criteria);
		// return this.task.getCompletionCriteria().isComplete(criteria);
	}

}