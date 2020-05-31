package com.prulloac.springdataextras.specification;

import com.prulloac.springdataextras.schema.DummyEntity;
import com.prulloac.springdataextras.annotation.FilterableColumn;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class SpecificationBuilderTest {

  @Test
  public void testClass() {
    String[] validFilters = new String[] {"field:isNull", "otherField:gte:1"};
    String[] inValidFilters = new String[] {"fieldNotFilterable:isNull", "fieldNotColumn:eq:1"};
    assertThat(SpecificationBuilder.build(null, null), nullValue());
    assertThat(SpecificationBuilder.build(validFilters, DummyEntity.class), notNullValue());
    assertThat(SpecificationBuilder.build(inValidFilters, DummyEntity.class), nullValue());
    assertThat(SpecificationBuilder.build(validFilters, PojoNotEntity.class), nullValue());
  }

  class PojoNotEntity {
    @FilterableColumn @Column String field;
  }
}
