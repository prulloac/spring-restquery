package com.prulloac.springdataversionableframework.schema;

import javax.persistence.Transient;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** @author Prulloac */
public interface VersionableEntity<T extends BaseVersionEntity<?>> {

  @Transient
  default T getCurrentVersion() {
    return getVersions().stream()
        .filter(Objects::nonNull)
        .max(Comparator.naturalOrder())
        .orElseThrow();
  }

  @Transient
  default T getOldestVersion() {
    return getVersions().stream()
        .filter(Objects::nonNull)
        .min(Comparator.naturalOrder())
        .orElseThrow();
  }

  List<T> getVersions();
}
