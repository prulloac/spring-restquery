package com.prulloac.springdataextras.specification;

/**
 * @author Prulloac
 */
public class SearchCondition {
	String field;
	QueryOperation operation;
	Object value;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public QueryOperation getOperation() {
		return operation;
	}

	public void setOperation(QueryOperation operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
