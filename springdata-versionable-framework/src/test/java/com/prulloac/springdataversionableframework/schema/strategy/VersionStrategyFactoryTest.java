package com.prulloac.springdataversionableframework.schema.strategy;

import com.prulloac.springdataversionableframework.errors.VersionStrategyNotFoundException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
              "com.prulloac.springdataversionableframework.schema.strategy.NonVersionStrategyImpl");
        });
  }

  @Test
  void testInvalidClassThatDoesImplementsVersionStrategy() {
    assertThrows(
        VersionStrategyNotFoundException.class,
        () -> {
          VersionStrategyFactory.getInstance(
              SomeVersionStrategyWithInvalidConstructor.class.getCanonicalName());
        });
    assertThrows(
        VersionStrategyNotFoundException.class,
        () -> {
          VersionStrategyFactory.getInstance(
              "com.prulloac.springdataversionableframework.schema.strategy.SomeVersionStrategyWithInvalidConstror");
        });
  }

  @Test
  void testValidClassThatImplementsVersionStrategy() {
    String className =
        "com.prulloac.springdataversionableframework.schema.strategy.SomeVersionStrategyImpl";
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

  @Test
  void testIllegalAccessToConstructor() throws Exception {
    Constructor<VersionStrategyFactory> constructor =
        VersionStrategyFactory.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    try {
      constructor.newInstance();
    } catch (Exception e) {
      assertThat(e, isA(InvocationTargetException.class));
      assertThat(e.getCause(), isA(IllegalStateException.class));
    }
  }
}
