package com.prulloac.springdataextras.schema.versioning.strategy;

import com.google.common.collect.ComparisonChain;
import com.prulloac.springdataextras.schema.versioning.BaseVersionEntity;

import java.io.Serializable;
import java.util.Comparator;

/** @author Prulloac */
public class Semver implements VersionStrategy {
  private static final String VERIFICATION_PATTERN = "^\\d+\\.\\d+\\.\\d+$";

  @Override
  public String nextVersion(String currentVersion) {
    String[] semverParts = currentVersion.split("\\.");
    String mayor = semverParts[0];
    String minor = semverParts[1];
    String fix = semverParts[2];
    String nextFix = String.valueOf(1 + Integer.parseInt(fix));
    return mayor + "." + minor + "." + nextFix;
  }

  @Override
  public String verificationPattern() {
    return VERIFICATION_PATTERN;
  }

  @Override
  public <T extends Serializable> Comparator<BaseVersionEntity<T>> comparator() {
    return (o1, o2) -> {
      String[] thisSemverParts = o1.getVersion().split("\\.");
      String[] thatSemverParts = o2.getVersion().split("\\.");
      return ComparisonChain.start()
          .compare(thisSemverParts[0], thatSemverParts[0])
          .compare(thisSemverParts[1], thatSemverParts[1])
          .compare(thisSemverParts[2], thatSemverParts[2])
          .result();
    };
  }
}
