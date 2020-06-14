package com.prulloac.springdataversionableframework.schema.strategy;

import com.prulloac.springdataversionableframework.schema.BaseVersionEntity;

import java.io.Serializable;
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
  public <T extends Serializable> Comparator<BaseVersionEntity<T>> comparator() {
    return null;
  }
}