package com.prulloac.springdataextras.restquery.operators;

import com.prulloac.springdataextras.utils.Constants;

import java.util.Arrays;
import java.util.stream.Collectors;

/** @author Prulloac */
public interface QueryOperator {

  String[] getRepresentations();

  default String getRegexForRepresentations() {
    return Arrays.stream(getRepresentations())
        .map(base -> Constants.REGEX_ESCAPED_CHARACTERS.matcher(base).replaceAll("\\\\$0"))
        .collect(Collectors.joining("|"));
  }
}
