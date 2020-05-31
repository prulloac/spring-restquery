package com.prulloac.springdataextras.schema;

import com.prulloac.springdataextras.schema.versioning.BaseVersionEntity;

public class DummyVersionEntity extends BaseVersionEntity<String> {

	public String content = "test";

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
