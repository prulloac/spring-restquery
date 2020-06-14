package com.prulloac.springdataextras.restquery.operators;

/** @author Prulloac */
public enum ComparisonOperator implements QueryOperator {
  EQUAL("==", "=", " == ", " = ", " eq ", " is ", " equals "),
  DISTINCT("!=", "<>", " != ", " <> ", " ne "),
  NULL("null", "NULL", "isNull", " null ", " NULL ", " isNull "),
  NOT_NULL("notNull", " notNull ", "isNotNull", " isNotNull "),
  GREATER_THAN(">", "gt", "greaterThan", " gt ", " greaterThan "),
  LESS_THAN("<", "lt", "lessThan", " lt ", " lessThan "),
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
