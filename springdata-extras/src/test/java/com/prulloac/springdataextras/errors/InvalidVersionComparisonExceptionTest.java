package com.prulloac.springdataextras.errors;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.isA;

class InvalidVersionComparisonExceptionTest {

  @Test
  void testConstructors() {
    String message = "Some message";
    try {
      throw new InvalidVersionComparisonException();
    } catch (Exception e) {
      assertThat(e, isA(InvalidVersionComparisonException.class));
    }
    try {
      throw new InvalidVersionComparisonException(message);
    } catch (Exception e) {
      assertThat(e, isA(InvalidVersionComparisonException.class));
      assertThat(e.getMessage(), containsString(message));
    }
    try {
      throw new InvalidVersionComparisonException(message, new Exception());
    } catch (Exception e) {
      assertThat(e, isA(InvalidVersionComparisonException.class));
      assertThat(e.getMessage(), containsString(message));
      assertThat(e.getCause(), isA(Exception.class));
    }
    try {
      throw new InvalidVersionComparisonException(new Exception());
    } catch (Exception e) {
      assertThat(e, isA(InvalidVersionComparisonException.class));
      assertThat(e.getCause(), isA(Exception.class));
    }
    try {
      throw new InvalidVersionComparisonException(message, new Exception(), false, false);
    } catch (Exception e) {
      assertThat(e, isA(InvalidVersionComparisonException.class));
      assertThat(e.getCause(), isA(Exception.class));
      assertThat(e.getSuppressed(), emptyArray());
      assertThat(e.getStackTrace(), emptyArray());
    }
  }
}
