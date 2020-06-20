package com.prulloac.springdata.restquery.nodes.comparison;

import com.prulloac.springdata.restquery.BaseRestQueryConfig;
import com.prulloac.springdata.restquery.specification.RestQuery;
import com.prulloac.springdata.restquery.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class LessThanNodeTest extends BaseRestQueryConfig {

  @Test
  void getPredicate() {
    String query = "id<10";
    String query2 = "id lt 2";
    String query3 = "state lt A";
    String query4 = "someBigDecimal lessThan 1";
    RestQuery<DummyEntity> restQuery = RestQuery.buildQuery(DummyEntity.class, query);
    RestQuery<DummyEntity> restQuery2 = RestQuery.buildQuery(DummyEntity.class, query2);
    RestQuery<DummyEntity> restQuery3 = RestQuery.buildQuery(DummyEntity.class, query3);
    RestQuery<DummyEntity> restQuery4 = RestQuery.buildQuery(DummyEntity.class, query4);
    List<DummyEntity> results = dummyEntityRepository.findAll(restQuery);
    List<DummyEntity> results2 = dummyEntityRepository.findAll(restQuery2);
    List<DummyEntity> results3 = dummyEntityRepository.findAll(restQuery3);
    List<DummyEntity> results4 = dummyEntityRepository.findAll(restQuery4);
    assertThat(results.size(), is(2));
    assertThat(results2.size(), is(1));
    assertThat(results3.size(), is(0));
    assertThat(results4.size(), is(1));
  }
}
