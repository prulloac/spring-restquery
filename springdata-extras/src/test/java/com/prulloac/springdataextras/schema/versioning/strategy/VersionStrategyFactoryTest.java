package com.prulloac.springdataextras.schema.versioning.strategy;

import com.prulloac.springdataextras.errors.VersionStrategyNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VersionStrategyFactoryTest {

  @Test
  void testInvalidClassName() {
    assertThrows(
        VersionStrategyNotFoundException.class,
        () -> {
          VersionStrategyFactory.getInstance("someClass");
        });
  }

  @Test
  void testInvalidClassThatDoesNotImplementsVersionStrategy() {
    assertThrows(
        VersionStrategyNotFoundException.class,
        () -> {
          VersionStrategyFactory.getInstance(NonVersionStrategyImpl.class.getCanonicalName());
        });
    assertThrows(
        VersionStrategyNotFoundException.class,
        () -> {
          VersionStrategyFactory.getInstance(
              "com.prulloac.springdataextras.schema.versioning.strategy.NonVersionStrategyImpl");
        });
  }

  @Test
  void testValidClassThatImplementsVersionStrategy() {
    String className =
        "com.prulloac.springdataextras.schema.versioning.strategy.SomeVersionStrategyImpl";
    assertDoesNotThrow(
        () -> {
          VersionStrategyFactory.getInstance(SomeVersionStrategyImpl.class.getCanonicalName());
        });
    assertDoesNotThrow(
        () -> {
          VersionStrategyFactory.getInstance(className);
        });
    assertEquals("SomeVersionStrategy", VersionStrategyFactory.getInstance(className).name());
  }
}
