package com.prulloac.springdataextras.schema.versioning.strategy;

import com.prulloac.springdataextras.schema.versioning.BaseVersionEntity;

import java.io.Serializable;
import java.util.Comparator;

/** @author Prulloac */
public class Draft implements VersionStrategy {
  private static final String VERIFICATION_PATTERN = "^DRAFT|VALIDATION|READY$";

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
  public <T extends Serializable> Comparator<BaseVersionEntity<T>> comparator() {
    return Comparator.comparing(o -> STAGES.valueOf(o.getVersion()));
  }

  public enum STAGES {
    DRAFT,
    VALIDATION,
    READY;
  }
}
