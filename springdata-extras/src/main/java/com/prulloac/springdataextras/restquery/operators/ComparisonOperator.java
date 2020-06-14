package com.prulloac.springdataextras.restquery.operators;

/** @author Prulloac */
public enum ComparisonOperator implements QueryOperator {
  EQUALS("==", "=", " == ", " = ", " eq ", " is ", " equals "),
  EQUALS_IGNORE_CASE(" equalsIgnoreCase "),
  DISTINCT("!=", "<>", " != ", " <> ", " ne "),
  NULL("null", "NULL", "isNull", " null ", " NULL ", " isNull "),
  NOT_NULL("notNull", " notNull ", "isNotNull", " isNotNull "),
  GREATER_THAN(">", "gt", "greaterThan", " gt ", " greaterThan "),
  LESS_THAN("<", "lt", "lessThan", " lt ", " lessThan "),
  GREATER_THAN_EQUALS(">=", "gte", "greaterThanEquals", " gte ", " greaterThanEquals "),
  LESS_THAN_EQUALS("<=", "lte", "lessThanEquals", " lte ", " lessThanEquals "),
  STARTS_WITH("startsWith", " startsWith ", "prefix", " prefix", "beginsWith", " beginsWith "),
  STARTS_WITH_IGNORE_CASE("startsWithIgnoreCase", " startsWithIgnoreCase ", "prefixIgnoreCase", " prefixIgnoreCase ", "beginsWithIgnoreCase", " beginsWithIgnoreCase "),
  ENDS_WITH("endsWith", " endsWith ", "suffix", " suffix ", "trailing", " trailing "),
  ENDS_WITH_IGNORE_CASE("endsWithIgnoreCase", " endsWithIgnoreCase ", "suffixIgnoreCase", " suffixIgnoreCase ", "trailingIgnoreCase", " trailingIgnoreCase "),
  CONTAINS("contains", " contains ", "like", " like "),
  CONTAINS_IGNORE_CASE("containsIgnoreCase", " containsIgnoreCase ", "likeIgnoreCase", " likeIgnoreCase "),
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
