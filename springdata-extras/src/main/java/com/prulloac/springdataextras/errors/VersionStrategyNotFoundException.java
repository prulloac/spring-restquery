package com.prulloac.springdataextras.errors;

/** @author Prulloac */
public class VersionStrategyNotFoundException extends RuntimeException {
  public VersionStrategyNotFoundException() {
    super();
  }

  public VersionStrategyNotFoundException(String message) {
    super(message);
  }

  public VersionStrategyNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public VersionStrategyNotFoundException(Throwable cause) {
    super(cause);
  }

  protected VersionStrategyNotFoundException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
