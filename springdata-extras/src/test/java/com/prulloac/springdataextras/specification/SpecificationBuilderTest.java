package com.prulloac.springdataextras.specification;

import com.prulloac.springdataextras.Pojo;
import com.prulloac.springdataextras.annotation.FilterableColumn;
import com.prulloac.springdataextras.annotation.SorteableColumn;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class SpecificationBuilderTest {

	class PojoNotEntity {
		@FilterableColumn
		@Column
		String field;
	}

	@Test
	public void testClass() {
		String[] validFilters = new String[]{"field:isNull", "otherField:gte:1"};
		String[] inValidFilters = new String[]{"fieldNotFilterable:isNull", "fieldNotColumn:eq:1"};
		assertThat(SpecificationBuilder.build(null, null), nullValue());
		assertThat(SpecificationBuilder.build(validFilters, Pojo.class), notNullValue());
		assertThat(SpecificationBuilder.build(inValidFilters, Pojo.class), nullValue());
		assertThat(SpecificationBuilder.build(validFilters, PojoNotEntity.class), nullValue());
	}
}
