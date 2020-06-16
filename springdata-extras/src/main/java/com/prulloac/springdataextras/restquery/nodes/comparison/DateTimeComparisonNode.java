package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.google.re2j.Pattern;
import com.prulloac.springdataextras.errors.RestQueryNodeCreationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class DateTimeComparisonNode extends ComparisonNode {
  private static final String DATE_REGEX =
      "(?:[1-9]\\d{3}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[1-9]\\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00)-02-29)";
  private static final String TIME_REGEX = "(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d(?:\\.\\d{1,9})?";
  private static final String ZONE_REGEX = "(?:Z|[+-][01]\\d:[0-5]\\d)";
  private static final Pattern DATE_PATTERN = Pattern.compile("^" + DATE_REGEX + "$");
  private static final Pattern DATE_TIME_PATTERN =
      Pattern.compile("^" + DATE_REGEX + "(T|\\s)" + TIME_REGEX + "$");
  private static final Pattern ZONED_DATE_TIME_PATTERN =
      Pattern.compile("^" + DATE_REGEX + "(T|\\s)" + TIME_REGEX + ZONE_REGEX + "$");

  protected DateTimeComparisonNode(String field, List<Object> arguments) {
    super(field, arguments);
  }

  protected DateTimeComparisonNode(String field, Object value) {
    super(field, value);
  }

  public static boolean isValidValue(String argument) {
    return DATE_PATTERN.matches(argument)
        || DATE_TIME_PATTERN.matches(argument)
        || ZONED_DATE_TIME_PATTERN.matches(argument);
  }

  public static void assertValueSyntax(String argument) {
    if (!isValidValue(argument)) {
      throw new RestQueryNodeCreationException();
    }
  }

  protected boolean isZonedDatetime(Class<?> type) {
    return type.equals(ZonedDateTime.class);
  }

  protected ZonedDateTime parseAsZonedDatetime(String originalValue) {
    String fixed = originalValue.replace(" ", "T");
    if (!ZONED_DATE_TIME_PATTERN.matches(fixed)) {
      return parseAsLocalDatetime(fixed).atZone(ZoneId.systemDefault());
    }
    return ZonedDateTime.parse(fixed, DateTimeFormatter.ISO_ZONED_DATE_TIME);
  }

  protected LocalDateTime parseAsLocalDatetime(String originalValue) {
    String fixed = originalValue.replace(" ", "T");
    if (DATE_TIME_PATTERN.matches(fixed)) {
      return LocalDateTime.parse(fixed, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    if (DATE_PATTERN.matches(fixed)) {
      return LocalDate.parse(fixed, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
    }
    throw new RestQueryNodeCreationException();
  }
}
