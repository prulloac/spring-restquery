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
		value = null;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SearchCondition that = (SearchCondition) o;
		return Objects.equal(field, that.field) &&
				operation == that.operation &&
				Objects.equal(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(field, operation, value);
	}
}
