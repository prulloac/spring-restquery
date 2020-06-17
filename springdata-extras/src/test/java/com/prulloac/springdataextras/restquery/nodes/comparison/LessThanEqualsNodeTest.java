package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.prulloac.springdataextras.restquery.BaseRestQueryConfig;
import com.prulloac.springdataextras.restquery.specification.RestQuery;
import com.prulloac.springdataextras.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class LessThanEqualsNodeTest extends BaseRestQueryConfig {

  @Test
  void getPredicate() {
    String query = "id<=10";
    String query2 = "id lte 1";
    String query3 = "state <= A";
    String query4 = "someBigDecimal lessThanEquals 1";
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
    assertThat(results3.size(), is(1));
    assertThat(results4.size(), is(2));
  }
}
