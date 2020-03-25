package org.eclipse.ice.modeling.states;

public class TestClass {

	private String attr;
	private String attrWithGetSet;
	private String attrThenGetSet;
	private String attrCodeGetSet;

	public String getAttrWithGetSet() {
		return this.attrWithGetSet;
	}

	/**
	 * 
	 * @param attrWithGetSet
	 */
	public void setAttrWithGetSet(String attrWithGetSet) {
		this.attrWithGetSet = attrWithGetSet;
	}

	public String getAttrThenGetSet() {
		return this.attrThenGetSet;
	}

	/**
	 * 
	 * @param attrThenGetSet
	 */
	public void setAttrThenGetSet(String attrThenGetSet) {
		this.attrThenGetSet = attrThenGetSet;
	}
	/**
	 * JXH Comment for method
	 * @return
	 */
	public String getAttrCodeGetSet() {
		System.out.println("TestClass.getAttrCodeGetSet()");
		return this.attrCodeGetSet;
	}

	/**
	 * 
	 * @param attrCodeGetSet
	 */
	public void setAttrCodeGetSet(String attrCodeGetSet) {
		this.attrCodeGetSet = attrCodeGetSet;
	}


}