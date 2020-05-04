package com.prulloac.springdataextras.specification;

import com.google.common.base.Objects;

/**
 * @author Prulloac
 */
public class SearchCondition {
	String field;
	QueryOperation operation;
	Object value;

	public SearchCondition() {
		field = "id";
		value = 1;
		operation = QueryOperation.EQUALS;
	}

	public SearchCondition(String field, QueryOperation operation, Object value) {
		this.field = field;
		this.operation = operation;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public SearchCondition setField(String field) {
		this.field = field;
		return this;
	}

	public QueryOperation getOperation() {
		return operation;
	}

	public SearchCondition setOperation(QueryOperation operation) {
		this.operation = operation;
		return this;
	}

	public Object getValue() {
		return value;
	}

	public SearchCondition setValue(Object value) {
		this.value = value;
		return this;
	}

	@Override
	public boolean equals(Object thatObject) {
		if (this == thatObject) return true;
		if (thatObject == null || this.getClass() != thatObject.getClass()) return false;
		SearchCondition that = (SearchCondition) thatObject;
		return Objects.equal(this.field, that.field) &&
				this.operation == that.operation &&
				Objects.equal(this.value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(field, operation, value);
	}
}
