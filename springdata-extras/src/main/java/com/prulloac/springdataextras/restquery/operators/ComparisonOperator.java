package com.prulloac.springdataextras.restquery.operators;

/** @author Prulloac */
public enum ComparisonOperator implements QueryOperator {
  EQUALS("==", "=", " == ", " = ", " eq ", " is ", " equals "),
  EQUALS_IGNORE_CASE(" equalsIgnoreCase "),
  DISTINCT("!=", "<>", " != ", " <> ", " ne "),
  IN(" in ", " inValues "),
  NULL(" null", " NULL", " isNull"),
  NOT_NULL(" notNull", " isNotNull"),
  GREATER_THAN(">", " gt ", " greaterThan "),
  LESS_THAN("<", " lt ", " lessThan "),
  GREATER_THAN_EQUALS(">=", " gte ", " greaterThanEquals "),
  LESS_THAN_EQUALS("<=", " lte ", " lessThanEquals "),
  STARTS_WITH(" startsWith ", " prefix", " beginsWith "),
  STARTS_WITH_IGNORE_CASE(" startsWithIgnoreCase ", " prefixIgnoreCase ", " beginsWithIgnoreCase "),
  ENDS_WITH(" endsWith ", " suffix ", " trailing "),
  ENDS_WITH_IGNORE_CASE(" endsWithIgnoreCase ", " suffixIgnoreCase ", " trailingIgnoreCase "),
  CONTAINS(" contains ", " like "),
  CONTAINS_IGNORE_CASE(" containsIgnoreCase ", " likeIgnoreCase "),
  BEFORE(" before ", " isBefore ", " isBeforeThan ", " beforeThan "),
  AFTER(" after ", " isAfter ", " isAfterThan ", " afterThan "),
  BETWEEN(" between ", " isBetween "),
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
