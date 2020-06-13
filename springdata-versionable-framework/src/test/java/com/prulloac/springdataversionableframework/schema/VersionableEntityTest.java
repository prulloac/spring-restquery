package com.prulloac.springdataversionableframework.schema;

import com.prulloac.springdataversionableframework.schema.strategy.Identity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionableEntityTest {
  DummyVersionableEntity entity;
  DummyVersionEntity version1;
  DummyVersionEntity version2;

  @BeforeEach
  void setUp() {
    entity = new DummyVersionableEntity();
    version1 = new DummyVersionEntity();
    version2 = new DummyVersionEntity();
    version1.setVersionStrategyImplementation(Identity.class);
    version1.setVersion("1");
    version2.setVersionStrategyImplementation(Identity.class);
    version2.setVersion("2");
    entity.versions = new ArrayList<>();
    entity.versions.add(version1);
    entity.versions.add(version2);
  }

  @Test
  void getCurrentVersion() {
    assertEquals(version2, entity.getCurrentVersion());
  }

  @Test
  void getOldestVersion() {
    assertEquals(version1, entity.getOldestVersion());
  }
}
