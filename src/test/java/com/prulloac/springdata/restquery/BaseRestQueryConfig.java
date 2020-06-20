package com.prulloac.springdata.restquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.prulloac.springdata.restquery.repositories.DummyEntityRepository;
import com.prulloac.springdata.restquery.schema.DummyContainerEntity;
import com.prulloac.springdata.restquery.schema.DummyEntity;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@DataJpaTest
public class BaseRestQueryConfig {

  @Autowired public TestEntityManager testEntityManager;
  @Autowired public DummyEntityRepository dummyEntityRepository;

  @BeforeEach
  public void init() {
    DummyContainerEntity dummyContainerEntity = new DummyContainerEntity();
    dummyContainerEntity.id = 1L;
    testEntityManager.persistAndFlush(dummyContainerEntity);
    DummyContainerEntity dummyContainerEntity2 = new DummyContainerEntity();
    dummyContainerEntity2.id = 2L;
    testEntityManager.persistAndFlush(dummyContainerEntity2);
    DummyEntity dummyEntity = new DummyEntity();
    dummyEntity.id = 1L;
    dummyEntity.field = "test";
    dummyEntity.container = dummyContainerEntity2;
    dummyEntity.birth = LocalDate.of(1991, 1, 30);
    dummyEntity.register = ZonedDateTime.of(2020, 1, 1, 1, 0, 0, 0, ZoneId.systemDefault());
    dummyEntity.state = DummyEntity.STATE.B;
    dummyEntity.someBigDecimal = new BigDecimal("1");
    dummyEntity.someDouble = 22D;
    dummyEntity.someFloat = 123F;
    dummyEntity.someShort = 1;
    dummyEntity.someInteger = 10;
    testEntityManager.persistAndFlush(dummyEntity);
    DummyEntity dummyEntity2 = new DummyEntity();
    dummyEntity2.id = 2L;
    dummyEntity2.field = "testB";
    dummyEntity2.state = DummyEntity.STATE.A;
    dummyEntity2.someBigDecimal = new BigDecimal("0");
    dummyEntity2.someDouble = 0D;
    dummyEntity2.someFloat = 0F;
    dummyEntity2.someShort = 0;
    dummyEntity2.someInteger = 0;
    testEntityManager.persistAndFlush(dummyEntity2);
  }
}
