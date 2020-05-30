package com.prulloac.springdataextras.schema.versioning.strategy;

import com.prulloac.springdataextras.schema.versioning.BaseVersionEntity;

import java.util.Comparator;

public class SomeVersionStrategyImpl implements VersionStrategy {

  @Override
  public String name() {
    return "SomeVersionStrategy";
  }

  @Override
  public String nextVersion(String currentVersion) {
    return null;
  }

  @Override
  public String verificationPattern() {
    return null;
  }

  @Override
  public Comparator<BaseVersionEntity> comparator() {
    return null;
  }
}
