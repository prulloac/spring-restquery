package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.prulloac.springdataextras.restquery.BaseRestQueryConfig;
import com.prulloac.springdataextras.restquery.specification.RestQuery;
import com.prulloac.springdataextras.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BetweenNodeTest extends BaseRestQueryConfig {

  @Test
  void getPredicate() {
    String query = "birth between 1980-12-31,2020-01-01";
    String query2 = "register between 2020-01-01 00:00:00,2020-02-01T00:00:00.123+09:00";
    String queryFail = "register between 2020-01-01 00:00:00;2020-02-01T00:00:00.123+09:00";
    RestQuery<DummyEntity> restQuery = RestQuery.buildQuery(DummyEntity.class, query);
    RestQuery<DummyEntity> restQuery2 = RestQuery.buildQuery(DummyEntity.class, query2);
    assertThrows(
        RuntimeException.class,
        () -> {
          RestQuery.buildQuery(DummyEntity.class, queryFail);
        });
    List<DummyEntity> results = dummyEntityRepository.findAll(restQuery);
    List<DummyEntity> results2 = dummyEntityRepository.findAll(restQuery2);
    assertThat(results.size(), is(1));
    assertThat(results2, not(empty()));
  }
}
