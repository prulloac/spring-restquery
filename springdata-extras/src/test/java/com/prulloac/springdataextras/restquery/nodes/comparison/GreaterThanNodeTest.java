package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.prulloac.springdataextras.restquery.BaseRestQueryConfig;
import com.prulloac.springdataextras.restquery.specification.RestQuery;
import com.prulloac.springdataextras.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GreaterThanNodeTest extends BaseRestQueryConfig {

  @Test
  void getPredicate() {
    String query = "id>0";
    String query2 = "id greaterThan 1";
    String query3 = "state greaterThan A";
    String query4 = "someDouble greaterThan 2";
    String query5 = "someInteger>1";
    String query6 = "someFloat > 2";
    String query7 = "someShort > 0";
    String query8 = "someBigDecimal greaterThan -1";
    RestQuery<DummyEntity> restQuery = RestQuery.buildQuery(DummyEntity.class, query);
    RestQuery<DummyEntity> restQuery2 = RestQuery.buildQuery(DummyEntity.class, query2);
    RestQuery<DummyEntity> restQuery3 = RestQuery.buildQuery(DummyEntity.class, query3);
    RestQuery<DummyEntity> restQuery4 = RestQuery.buildQuery(DummyEntity.class, query4);
    RestQuery<DummyEntity> restQuery5 = RestQuery.buildQuery(DummyEntity.class, query5);
    RestQuery<DummyEntity> restQuery6 = RestQuery.buildQuery(DummyEntity.class, query6);
    RestQuery<DummyEntity> restQuery7 = RestQuery.buildQuery(DummyEntity.class, query7);
    RestQuery<DummyEntity> restQuery8 = RestQuery.buildQuery(DummyEntity.class, query8);
    List<DummyEntity> results = dummyEntityRepository.findAll(restQuery);
    List<DummyEntity> results2 = dummyEntityRepository.findAll(restQuery2);
    List<DummyEntity> results3 = dummyEntityRepository.findAll(restQuery3);
    List<DummyEntity> results4 = dummyEntityRepository.findAll(restQuery4);
    List<DummyEntity> results5 = dummyEntityRepository.findAll(restQuery5);
    List<DummyEntity> results6 = dummyEntityRepository.findAll(restQuery6);
    List<DummyEntity> results7 = dummyEntityRepository.findAll(restQuery7);
    List<DummyEntity> results8 = dummyEntityRepository.findAll(restQuery8);
    System.out.println(results3.toString());
    assertThat(results.size(), is(2));
    assertThat(results2.size(), is(1));
    assertThat(results3.size(), is(1));
    assertThat(results4.size(), is(1));
    assertThat(results5.size(), is(1));
    assertThat(results6.size(), is(1));
    assertThat(results7.size(), is(1));
    assertThat(results8.size(), is(2));
  }
}
