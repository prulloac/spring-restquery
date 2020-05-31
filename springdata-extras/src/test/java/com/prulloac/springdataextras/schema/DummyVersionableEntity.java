package com.prulloac.springdataextras.schema;

import com.prulloac.springdataextras.schema.versioning.VersionableEntity;

import java.util.List;

public class DummyVersionableEntity implements VersionableEntity<DummyVersionEntity> {
	public List<DummyVersionEntity> versions;

	@Override
	public List<DummyVersionEntity> getVersions() {
		return versions;
	}
}
