package com.prulloac.springdataextras.schema.versioning.strategy;

import com.google.re2j.Pattern;
import com.prulloac.springdataextras.schema.versioning.BaseVersionEntity;

import java.util.Comparator;

/** @author Prulloac */
public interface VersionStrategy {
  String name();

  String nextVersion(String currentVersion);

  String verificationPattern();

  default boolean isVersionValid(String version) {
    return Pattern.matches(verificationPattern(), version);
  }

  Comparator<BaseVersionEntity> comparator();
}
