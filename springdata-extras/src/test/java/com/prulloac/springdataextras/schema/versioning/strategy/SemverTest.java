package com.prulloac.springdataextras.schema.versioning.strategy;

import com.prulloac.springdataextras.schema.DummyVersionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class SemverTest {
  DummyVersionEntity version1;
  DummyVersionEntity version2;
  VersionStrategy strategy = new Semver();

  @BeforeEach
  void setUp() {
    version1 = new DummyVersionEntity();
    version2 = new DummyVersionEntity();
    version1.setVersionStrategyImplementation(Semver.class);
    version1.setVersion("1.0.0");
    version2.setVersionStrategyImplementation(Semver.class);
    version2.setVersion("2.0.0");
  }

  @Test
  void nextVersion() {
    assertEquals("1.0.1", strategy.nextVersion("1.0.0"));
    assertEquals("1.0.2", strategy.nextVersion("1.0.1"));
    assertEquals("2.0.1", strategy.nextVersion("2.0.0"));
  }

  @Test
  void verificationPattern() {
    assertFalse(strategy.isVersionValid("1"));
    assertFalse(strategy.isVersionValid("1.0"));
    assertTrue(strategy.isVersionValid("1.0.0"));
    assertFalse(strategy.isVersionValid("2020-Q1"));
    assertFalse(strategy.isVersionValid("DRAFT"));
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