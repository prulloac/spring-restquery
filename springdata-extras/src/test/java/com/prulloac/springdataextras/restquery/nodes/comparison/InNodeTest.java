package com.prulloac.springdataextras.restquery.nodes.comparison;

import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.prulloac.springdataextras.errors.RestQueryException;
import com.prulloac.springdataextras.restquery.BaseRestQueryConfig;
import com.prulloac.springdataextras.restquery.specification.RestQuery;
import com.prulloac.springdataextras.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

class InNodeTest extends BaseRestQueryConfig {

  @Test
  void getPredicate() {
    String query = "id in (1)";
    String query2 = "field inValues (testB, testA, testC)";
    String queryFail = "birth in (1991-01-30)";
    RestQuery<DummyEntity> restQuery = RestQuery.buildQuery(DummyEntity.class, query);
    RestQuery<DummyEntity> restQuery2 = RestQuery.buildQuery(DummyEntity.class, query2);
    assertThrows(
            RestQueryException.class,
            () -> {
              dummyEntityRepository.findAll(RestQuery.buildQuery(DummyEntity.class, queryFail));
            });
    List<DummyEntity> results = dummyEntityRepository.findAll(restQuery);
    List<DummyEntity> results2 = dummyEntityRepository.findAll(restQuery2);
    assertThat(results.size(), is(1));
    assertThat(results2, not(empty()));
  }
}