package com.prulloac.springdataversionableframework.schema;

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
