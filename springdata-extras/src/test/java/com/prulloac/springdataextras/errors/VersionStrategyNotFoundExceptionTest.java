package com.prulloac.springdataextras.errors;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.isA;

class VersionStrategyNotFoundExceptionTest {

  @Test
  void testConstructors() {
    String message = "Some message";
    try {
      throw new VersionStrategyNotFoundException();
    } catch (Exception e) {
      assertThat(e, isA(VersionStrategyNotFoundException.class));
    }
    try {
      throw new VersionStrategyNotFoundException(message);
    } catch (Exception e) {
      assertThat(e, isA(VersionStrategyNotFoundException.class));
      assertThat(e.getMessage(), containsString(message));
    }
    try {
      throw new VersionStrategyNotFoundException(message, new Exception());
    } catch (Exception e) {
      assertThat(e, isA(VersionStrategyNotFoundException.class));
      assertThat(e.getMessage(), containsString(message));
      assertThat(e.getCause(), isA(Exception.class));
    }
    try {
      throw new VersionStrategyNotFoundException(new Exception());
    } catch (Exception e) {
      assertThat(e, isA(VersionStrategyNotFoundException.class));
      assertThat(e.getCause(), isA(Exception.class));
    }
    try {
      throw new VersionStrategyNotFoundException(message, new Exception(), false, false);
    } catch (Exception e) {
      assertThat(e, isA(VersionStrategyNotFoundException.class));
      assertThat(e.getCause(), isA(Exception.class));
      assertThat(e.getSuppressed(), emptyArray());
      assertThat(e.getStackTrace(), emptyArray());
    }
  }
}
