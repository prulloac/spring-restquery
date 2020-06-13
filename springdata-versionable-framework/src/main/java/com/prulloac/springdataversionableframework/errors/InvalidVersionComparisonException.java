package com.prulloac.springdataversionableframework.errors;

/** @author Prulloac */
public class InvalidVersionComparisonException extends RuntimeException {
  public InvalidVersionComparisonException() {
    super();
  }

  public InvalidVersionComparisonException(String message) {
    super(message);
  }

  public InvalidVersionComparisonException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidVersionComparisonException(Throwable cause) {
    super(cause);
  }

  protected InvalidVersionComparisonException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
