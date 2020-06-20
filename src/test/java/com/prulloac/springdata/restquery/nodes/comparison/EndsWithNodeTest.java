package com.prulloac.springdata.restquery.nodes.comparison;

import com.prulloac.springdata.restquery.BaseRestQueryConfig;
import com.prulloac.springdata.restquery.specification.RestQuery;
import com.prulloac.springdata.restquery.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

class EndsWithNodeTest extends BaseRestQueryConfig {

  @Test
  void getPredicate() {
    String query = "field endsWith te";
    String query2 = "field suffix B";
    RestQuery<DummyEntity> restQuery = RestQuery.buildQuery(DummyEntity.class, query);
    RestQuery<DummyEntity> restQuery2 = RestQuery.buildQuery(DummyEntity.class, query2);
    List<DummyEntity> results = dummyEntityRepository.findAll(restQuery);
    List<DummyEntity> results2 = dummyEntityRepository.findAll(restQuery2);
    assertThat(results, empty());
    assertThat(results2, not(empty()));
  }
}
