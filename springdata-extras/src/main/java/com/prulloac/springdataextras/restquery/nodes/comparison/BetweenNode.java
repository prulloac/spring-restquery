package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.prulloac.springdataextras.errors.RestQueryNodeCreationException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BetweenNode extends DateTimeComparisonNode {
  public BetweenNode(String field, String values) {
    super(field, Arrays.asList(values.split(",")));
  }

  public static void assertValueSyntax(String argument) {
    if (!Arrays.stream(argument.split(",")).allMatch(DateTimeComparisonNode::isValidValue)) {
      throw new RestQueryNodeCreationException();
    }
  }

  @Override
  public Predicate getPredicate(Path<?> propertyPath, CriteriaBuilder criteriaBuilder) {
    Class<?> type = propertyPath.getJavaType();
    if (isZonedDatetime(type)) {
      List<ZonedDateTime> values = new ArrayList<>();
      for (Object value : getArguments()) {
        values.add(parseAsZonedDatetime((String) value));
      }
      ZonedDateTime lowerValue =
          values.stream().min(ChronoZonedDateTime::compareTo).orElse(ZonedDateTime.now());
      ZonedDateTime higherValue =
          values.stream().max(ChronoZonedDateTime::compareTo).orElse(ZonedDateTime.now());
      return criteriaBuilder.between(propertyPath.as(ZonedDateTime.class), lowerValue, higherValue);
    }
    List<LocalDateTime> values = new ArrayList<>();
    for (Object value : getArguments()) {
      values.add(parseAsLocalDatetime((String) value));
    }
    LocalDateTime lowerValue =
        values.stream().min(ChronoLocalDateTime::compareTo).orElse(LocalDateTime.now());
    LocalDateTime higherValue =
        values.stream().max(ChronoLocalDateTime::compareTo).orElse(LocalDateTime.now());
    return criteriaBuilder.between(propertyPath.as(LocalDateTime.class), lowerValue, higherValue);
  }
}
