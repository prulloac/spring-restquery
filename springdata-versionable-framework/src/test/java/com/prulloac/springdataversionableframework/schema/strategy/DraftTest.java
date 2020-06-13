package com.prulloac.springdataversionableframework.schema.strategy;

import com.prulloac.springdataversionableframework.schema.DummyVersionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DraftTest {
  DummyVersionEntity version1;
  DummyVersionEntity version2;
  VersionStrategy strategy = new Draft();

  @BeforeEach
  void setUp() {
    version1 = new DummyVersionEntity();
    version2 = new DummyVersionEntity();
    version1.setVersionStrategyImplementation(Draft.class);
    version1.setVersion("DRAFT");
    version2.setVersionStrategyImplementation(Draft.class);
    version2.setVersion("READY");
  }

  @Test
  void nextVersion() {
    assertEquals("VALIDATION", strategy.nextVersion("DRAFT"));
    assertEquals("READY", strategy.nextVersion("VALIDATION"));
    assertEquals("READY", strategy.nextVersion("READY"));
  }

  @Test
  void verificationPattern() {
    assertFalse(strategy.isVersionValid("1"));
    assertFalse(strategy.isVersionValid("1.0"));
    assertFalse(strategy.isVersionValid("1.0.0"));
    assertFalse(strategy.isVersionValid("2020-Q1"));
    assertTrue(strategy.isVersionValid("DRAFT"));
  }

  @Test
  void comparator() {
    List<DummyVersionEntity> dummyVersionEntities = Arrays.asList(version1, version2);
    dummyVersionEntities.sort(strategy.comparator());
    assertThat(dummyVersionEntities.get(0), equalTo(version1));
    dummyVersionEntities.sort(Comparator.reverseOrder());
    assertThat(dummyVersionEntities.get(0), equalTo(version2));
  }
}
