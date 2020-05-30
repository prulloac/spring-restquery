package com.prulloac.springdataextras.schema.versioning.strategy;

import com.prulloac.springdataextras.schema.versioning.BaseVersionEntity;

import java.util.Comparator;

/** @author Prulloac */
public class Identity implements VersionStrategy {
  private static final String STRATEGY_NAME = "Identity";
  private static final String VERIFICATION_PATTERN = "^\\d+$";

  @Override
  public String name() {
    return STRATEGY_NAME;
  }

  @Override
  public String nextVersion(String currentVersion) {
    return String.valueOf(1 + Integer.parseInt(currentVersion));
  }

  @Override
  public String verificationPattern() {
    return VERIFICATION_PATTERN;
  }

  @Override
  public Comparator<BaseVersionEntity> comparator() {
    return Comparator.comparing(o -> Long.valueOf(o.getVersion()));
  }
}
