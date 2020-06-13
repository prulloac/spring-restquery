package com.prulloac.springdataextras.utils;

import com.google.re2j.Pattern;

import static com.prulloac.springdataextras.specification.operators.LogicalOperator.NOT;

public class Constants {
  public static final Pattern REGEX_ESCAPED_CHARACTERS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");
  public static final Pattern REGEX_PATTERN_NOT_LOGICAL_NODE =
      Pattern.compile(NOT.getRegexForRepresentations());

  private Constants() throws IllegalAccessException {
    throw new IllegalAccessException();
  }
}
