package com.prulloac.springdataextras.utils;

import com.google.re2j.Pattern;

public class Constants {
  public static final Pattern REGEX_ESCAPED_CHARACTERS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");

  private Constants() throws IllegalAccessException {
    throw new IllegalAccessException();
  }
}
