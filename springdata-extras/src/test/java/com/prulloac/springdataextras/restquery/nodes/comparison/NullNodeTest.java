package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.prulloac.springdataextras.restquery.BaseRestQueryConfig;
import com.prulloac.springdataextras.restquery.specification.RestQuery;
import com.prulloac.springdataextras.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class NullNodeTest extends BaseRestQueryConfig {

  @Test
  void getPredicate() {
    String query = "id isNull";
    String query2 = "container.id null";
    RestQuery<DummyEntity> restQuery = RestQuery.buildQuery(DummyEntity.class, query);
    RestQuery<DummyEntity> restQuery2 = RestQuery.buildQuery(DummyEntity.class, query2);
    List<DummyEntity> results = dummyEntityRepository.findAll(restQuery);
    List<DummyEntity> results2 = dummyEntityRepository.findAll(restQuery2);
    assertThat(results.size(), is(0));
    assertThat(results2.size(), is(1));
  }
}
