package com.prulloac.springdataextras.schema.versioning.strategy;

import com.prulloac.springdataextras.schema.versioning.BaseVersionEntity;

import java.util.Comparator;

/** @author Prulloac */
public class Draft implements VersionStrategy {
  private static final String STRATEGY_NAME = "Draft";
  private static final String VERIFICATION_PATTERN = "^DRAFT|VALIDATION|READY$";

  public enum STAGES {
    DRAFT,
    VALIDATION,
    READY;
  }

  @Override
  public String name() {
    return STRATEGY_NAME;
  }

  @Override
  public String nextVersion(String currentVersion) {
    if (STAGES.DRAFT.name().equals(currentVersion)) {
      return STAGES.VALIDATION.name();
    }
    return STAGES.READY.name();
  }

  @Override
  public String verificationPattern() {
    return VERIFICATION_PATTERN;
  }

  @Override
  public Comparator<BaseVersionEntity> comparator() {
    return Comparator.comparing(o -> STAGES.valueOf(o.getVersion()));
  }
}
