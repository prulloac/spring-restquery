package com.prulloac.springdata.restquery.utils;

import com.google.re2j.Pattern;

/** @author Prulloac */
public class RegexConstants {
  public static final Pattern REGEX_ESCAPED_CHARACTERS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");

  private RegexConstants() throws IllegalAccessException {
    throw new IllegalAccessException();
  }
}
