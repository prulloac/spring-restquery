package com.prulloac.springdataextras.specification;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchConditionTest {
	SearchCondition searchCondition;

	@Test
	public void testPOJOMethods() {
		String nameStr = "name";
		QueryOperation startsWith = QueryOperation.STARTS_WITH;
		String testStr = "test";
		searchCondition = new SearchCondition();
		assertThat(searchCondition.getField(), equalTo("id"));
		assertThat(searchCondition.getOperation(), equalTo(QueryOperation.EQUALS));
		assertThat(searchCondition.getValue(), nullValue());
		SearchCondition searchCondition2 = new SearchCondition(nameStr, startsWith, testStr);
		assertThat(searchCondition.setField(nameStr), hasProperty("field", equalTo(nameStr)));
		assertThat(searchCondition.setOperation(startsWith), hasProperty("operation", equalTo(startsWith)));
		assertThat(searchCondition.setValue(testStr), hasProperty("value", equalTo(testStr)));
		assertEquals(searchCondition, searchCondition2);
		assertEquals(searchCondition.hashCode(), searchCondition2.hashCode());
		assertTrue(searchCondition.equals(searchCondition));
		assertFalse(searchCondition.equals(testStr));
		assertFalse(searchCondition.equals(null));
		assertFalse(searchCondition.equals(searchCondition2.setField("id")));
		searchCondition.setField("id");
		assertFalse(searchCondition.equals(searchCondition2.setValue(null)));
		searchCondition.setValue(null);
		assertFalse(searchCondition.equals(searchCondition2.setOperation(QueryOperation.CONTAINS)));
	}

}
