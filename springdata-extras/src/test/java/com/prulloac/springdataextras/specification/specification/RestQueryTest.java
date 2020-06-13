package com.prulloac.springdataextras.specification.specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.prulloac.springdataextras.repositories.DummyEntityRepository;
import com.prulloac.springdataextras.schema.DummyContainerEntity;
import com.prulloac.springdataextras.schema.DummyEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
public class RestQueryTest {

  @Autowired TestEntityManager testEntityManager;
  @Autowired DummyEntityRepository dummyEntityRepository;

  @BeforeEach
  public void init() {
    DummyContainerEntity dummyContainerEntity = new DummyContainerEntity();
    dummyContainerEntity.id = 1L;
    DummyContainerEntity dummyContainerEntity2 = new DummyContainerEntity();
    dummyContainerEntity2.id = 2L;
    testEntityManager.persistAndFlush(dummyContainerEntity);
    testEntityManager.persistAndFlush(dummyContainerEntity2);
    DummyEntity dummyEntity = new DummyEntity();
    dummyEntity.id = 1L;
    dummyEntity.field = "test";
    dummyEntity.container = dummyContainerEntity2;
    DummyEntity dummyEntity2 = new DummyEntity();
    dummyEntity2.id = 2L;
    dummyEntity2.field = "testB";
    testEntityManager.persistAndFlush(dummyEntity);
    testEntityManager.persistAndFlush(dummyEntity2);
  }

  @Test
  public void testSpecification() {
    String query1 = "field=test";
    String query2 = "field!=test";
    String query3 = "container.id=2";
    String query4 = "field=test,testB";
    String query5 = "field!=test and field!=testB";
    String query6 = "field null";
    String query7 = "field not null";
    String query8 = "field notNull";
    RestQuery<DummyEntity> restQuery = new RestQuery<>(query1);
    RestQuery<DummyEntity> restQuery2 = new RestQuery<>(query2);
    RestQuery<DummyEntity> restQuery3 = new RestQuery<>(query3);
    RestQuery<DummyEntity> restQuery4 = new RestQuery<>(query4);
    RestQuery<DummyEntity> restQuery5 = new RestQuery<>(query5);
    RestQuery<DummyEntity> restQuery6 = new RestQuery<>(query6);
    RestQuery<DummyEntity> restQuery7 = new RestQuery<>(query7);
    RestQuery<DummyEntity> restQuery8 = new RestQuery<>(query8);
    assertNotEquals(restQuery.getQueryNode(), restQuery2.getQueryNode());
    List<DummyEntity> results1 = dummyEntityRepository.findAll(restQuery);
    List<DummyEntity> results2 = dummyEntityRepository.findAll(restQuery2);
    List<DummyEntity> results3 = dummyEntityRepository.findAll(restQuery3);
    List<DummyEntity> results4 = dummyEntityRepository.findAll(restQuery4);
    List<DummyEntity> results5 = dummyEntityRepository.findAll(restQuery5);
    List<DummyEntity> results6 = dummyEntityRepository.findAll(restQuery6);
    List<DummyEntity> results7 = dummyEntityRepository.findAll(restQuery7);
    List<DummyEntity> results8 = dummyEntityRepository.findAll(restQuery8);
    assertThat(results1, not(empty()));
    assertThat(
        results1.stream().map(DummyEntity::getField).collect(Collectors.toList()),
        hasItem(startsWith("test")));
    assertThat(results2, not(empty()));
    assertThat(results3, not(empty()));
    assertThat(results4, not(empty()));
    assertThat(results4.size(), is(2));
    assertThat(results5.size(), is(0));
    assertThat(results6.size(), is(0));
    assertThat(results7, not(empty()));
    assertThat(results7.size(), is(2));
    assertThat(results8, not(empty()));
    assertThat(results8.size(), is(2));
  }
}
