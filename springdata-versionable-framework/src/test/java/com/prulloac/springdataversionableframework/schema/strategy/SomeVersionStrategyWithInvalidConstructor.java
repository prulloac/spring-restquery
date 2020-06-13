package com.prulloac.springdataversionableframework.schema.strategy;

import com.prulloac.springdataversionableframework.schema.BaseVersionEntity;

import java.io.Serializable;
import java.util.Comparator;

public class SomeVersionStrategyWithInvalidConstructor implements VersionStrategy {

  private SomeVersionStrategyWithInvalidConstructor() {}

  @Override
  public String nextVersion(String currentVersion) {
    return null;
  }

  @Override
  public String verificationPattern() {
    return null;
  }

  @Override
  public <T extends Serializable> Comparator<BaseVersionEntity<T>> comparator() {
    return null;
  }
}
