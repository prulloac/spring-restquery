package com.prulloac.springdataextras.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class BeforeNode extends DateTimeComparisonNode {
  public BeforeNode(String field, String value) {
    super(field, value);
  }

  @Override
  public Predicate getPredicate(Path<?> propertyPath, CriteriaBuilder criteriaBuilder) {
    String originalValue = (String) getArguments().get(0);
    Class<?> type = propertyPath.getJavaType();
    if (isZonedDatetime(type)) {
      ZonedDateTime value = parseAsZonedDatetime(originalValue);
      return criteriaBuilder.lessThanOrEqualTo(propertyPath.as(ZonedDateTime.class), value);
    }
    LocalDateTime value = parseAsLocalDatetime(originalValue);
    return criteriaBuilder.lessThanOrEqualTo(propertyPath.as(LocalDateTime.class), value);
  }
}
