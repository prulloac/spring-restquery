package com.prulloac.springdataextras.schema.versioning.strategy;

import com.google.re2j.Pattern;
import com.prulloac.springdataextras.schema.versioning.BaseVersionEntity;

import java.io.Serializable;
import java.util.Comparator;

/** @author Prulloac */
public interface VersionStrategy {

  default String name() {
    return getClass().getSimpleName();
  }

  String nextVersion(String currentVersion);

  String verificationPattern();

  default boolean isVersionValid(String version) {
    return Pattern.matches(verificationPattern(), version);
  }

  <T extends Serializable> Comparator<BaseVersionEntity<T>> comparator();
}
