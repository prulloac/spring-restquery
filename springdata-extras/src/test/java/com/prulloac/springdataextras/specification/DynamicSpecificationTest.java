package com.prulloac.springdataextras.specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.prulloac.springdataextras.schema.DummyEntity;
import com.prulloac.springdataextras.repositories.DummyEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.prulloac.springdataextras.specification.DynamicSpecification.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class DynamicSpecificationTest {

  @Autowired TestEntityManager testEntityManager;
  @Autowired
  DummyEntityRepository dummyEntityRepository;

  @BeforeEach
  public void init() {
    DummyEntity dummyEntity = new DummyEntity();
    dummyEntity.id = 1L;
    dummyEntity.field = "test";
    testEntityManager.persistAndFlush(dummyEntity);
  }

  @Test
  public void testSpecification() {
    SearchCondition searchCondition = new SearchCondition();
    DynamicSpecification<DummyEntity> dynamicSpecification = new DynamicSpecification<>(searchCondition);
    assertThat(dummyEntityRepository.findAll(dynamicSpecification), everyItem(notNullValue()));
    assertThat(
        dummyEntityRepository.findAll(
            of(searchCondition.setOperation(QueryOperation.GREATER_THAN_EQUALS))),
        everyItem(notNullValue()));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.LESS_THAN_EQUALS))),
        everyItem(notNullValue()));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.GREATER_THAN))),
        everyItem(nullValue()));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.LESS_THAN))),
        everyItem(nullValue()));
    assertThrows(
        InvalidDataAccessApiUsageException.class,
        () -> {
          dummyEntityRepository.findAll(
              of(searchCondition.setValue("t").setOperation(QueryOperation.CONTAINS)));
        });
    assertThat(
        dummyEntityRepository.findAll(
            of(
                searchCondition
                    .setField("field")
                    .setValue("t")
                    .setOperation(QueryOperation.CONTAINS))),
        everyItem(notNullValue()));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.LIKE_IGNORE_CASE))),
        everyItem(notNullValue()));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.STARTS_WITH))),
        everyItem(notNullValue()));
    assertThat(
        dummyEntityRepository.findAll(
            of(searchCondition.setOperation(QueryOperation.STARTS_WITH_IGNORE_CASE))),
        everyItem(notNullValue()));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.ENDS_WITH))),
        everyItem(notNullValue()));
    assertThat(
        dummyEntityRepository.findAll(
            of(searchCondition.setOperation(QueryOperation.ENDS_WITH_IGNORE_CASE))),
        everyItem(notNullValue()));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.EQUALS_IGNORE_CASE))),
        everyItem(nullValue()));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setValue("tesT"))), everyItem(notNullValue()));
    DummyEntity dummyEntity = new DummyEntity();
    dummyEntity.id = 2L;
    dummyEntity.field = null;
    testEntityManager.persistAndFlush(dummyEntity);
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.LIKE_OR_NULL))),
        iterableWithSize(1));
    assertThat(
        dummyEntityRepository.findAll(
            of(searchCondition.setOperation(QueryOperation.LIKE_OR_NULL_IGNORE_CASE))),
        iterableWithSize(2));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.IS_NULL))),
        iterableWithSize(1));
    assertThat(
        dummyEntityRepository.findAll(
            of(searchCondition.setField("id").setOperation(QueryOperation.NOT_NULL))),
        iterableWithSize(2));
    assertThat(
        dummyEntityRepository.findAll(
            of(searchCondition.setField("id").setOperation(QueryOperation.NOT_NULL))),
        iterableWithSize(2));
    assertThat(
        dummyEntityRepository.findAll(of(searchCondition.setOperation(QueryOperation.IN).setValue("1,2"))),
        iterableWithSize(2));
    assertThat(
        dummyEntityRepository.findAll(
            of(searchCondition.setOperation(QueryOperation.NOT_IN).setValue("2,3,4,5"))),
        iterableWithSize(1));
    assertThrows(
        NullPointerException.class,
        () -> {
          dummyEntityRepository.findAll(of(searchCondition.setOperation(null)));
        });
  }
}
