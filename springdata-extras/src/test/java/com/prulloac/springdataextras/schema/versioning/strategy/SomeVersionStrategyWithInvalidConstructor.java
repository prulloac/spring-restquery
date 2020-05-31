package com.prulloac.springdataextras.schema.versioning.strategy;

import com.prulloac.springdataextras.schema.versioning.BaseVersionEntity;

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
