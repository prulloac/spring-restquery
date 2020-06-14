package com.prulloac.springdataextras.restquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.prulloac.springdataextras.repositories.DummyEntityRepository;
import com.prulloac.springdataextras.schema.DummyContainerEntity;
import com.prulloac.springdataextras.schema.DummyEntity;
import org.junit.jupiter.api.BeforeEach;

@DataJpaTest
public class BaseRestQueryTest {

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
    testEntityManager.persistAndFlush(dummyEntity);
    DummyEntity dummyEntity2 = new DummyEntity();
    dummyEntity2.id = 2L;
    dummyEntity2.field = "testB";
    testEntityManager.persistAndFlush(dummyEntity2);
  }
}
