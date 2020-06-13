package com.prulloac.springdataextras.specification.operators;

/** @author Prulloac */
public enum ComparisonOperator implements QueryOperator {
  EQUAL("==", "=", ":eq:", ":is:", ":equals:"),
  DISTINCT("!=", "<>", ":ne:"),
  NULL("null", "NULL", "isNull"),
  NOT_NULL("notNull"),
  ;

  private final String[] representations;

  ComparisonOperator(String... representations) {
    this.representations = representations;
  }

  @Override
  public String[] getRepresentations() {
    return representations;
  }
}
