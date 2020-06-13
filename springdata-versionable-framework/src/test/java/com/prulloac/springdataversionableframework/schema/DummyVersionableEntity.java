package com.prulloac.springdataversionableframework.schema;

import java.util.List;

public class DummyVersionableEntity implements VersionableEntity<DummyVersionEntity> {
  public List<DummyVersionEntity> versions;

  @Override
  public List<DummyVersionEntity> getVersions() {
    return versions;
  }
}
