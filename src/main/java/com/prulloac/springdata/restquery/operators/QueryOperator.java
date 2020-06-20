package com.prulloac.springdata.restquery.operators;

import com.prulloac.springdata.restquery.utils.RegexConstants;

import java.util.Arrays;
import java.util.stream.Collectors;

/** @author Prulloac */
public interface QueryOperator {

  String[] getRepresentations();

  default String getRegexForRepresentations() {
    return Arrays.stream(getRepresentations())
        .map(base -> RegexConstants.REGEX_ESCAPED_CHARACTERS.matcher(base).replaceAll("\\\\$0"))
        .collect(Collectors.joining("|"));
  }
}
