package com.prulloac.springdataextras.specification.operators;

/** @author Prulloac */
public enum LogicalOperator implements QueryOperator {
  AND(" AND ", " and ", " && "),
  OR(" OR ", " or ", " || "),
  NOT(" NOT ", " not ", " !! "),
  ;

  private final String[] representations;

  LogicalOperator(String... representations) {
    this.representations = representations;
  }

  @Override
  public String[] getRepresentations() {
    return representations;
  }
}
