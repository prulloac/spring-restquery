package com.prulloac.springdata.restquery.specification;

import com.prulloac.springdata.restquery.BaseRestQueryConfig;
import com.prulloac.springdata.restquery.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

class RestQueryTest extends BaseRestQueryConfig {

  @Test
  void testSpecification() {
    String query1 = "field=test";
    String query2 = "container.id=2";
    String query3 = "field==test and id=2";
    RestQuery<DummyEntity> restQuery = RestQuery.buildQuery(DummyEntity.class, query1);
    RestQuery<DummyEntity> restQuery2 = RestQuery.buildQuery(DummyEntity.class, query2);
    RestQuery<DummyEntity> restQuery3 = RestQuery.buildQuery(DummyEntity.class, query3);
    List<DummyEntity> results1 = dummyEntityRepository.findAll(restQuery);
    List<DummyEntity> results2 = dummyEntityRepository.findAll(restQuery2);
    List<DummyEntity> results3 = dummyEntityRepository.findAll(restQuery3);
    assertThat(results1, not(empty()));
    assertThat(
        results1.stream().map(DummyEntity::getField).collect(Collectors.toList()),
        hasItem(startsWith("test")));
    assertThat(results2, not(empty()));
    assertThat(results3, empty());
  }
}
