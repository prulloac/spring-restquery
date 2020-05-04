package com.prulloac.springdataextras.specification;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class QueryOperationTest {

	@Test
	public void testGetterAndParse() {
		QueryOperation queryOperation = QueryOperation.GREATER_THAN;
		assertThat(QueryOperation.parse("isEqual"), nullValue());
		assertThat(QueryOperation.parse("eq"), notNullValue());
		assertThat(queryOperation.getIdentifier(), equalTo("gt"));
	}

}
