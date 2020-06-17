package com.prulloac.springdataextras.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;

/** @author Prulloac */
public class LessThanEqualsNode extends NumericComparisonNode {
  public LessThanEqualsNode(String field, String value) {
    super(field, value);
  }

  @Override
  public Predicate getPredicate(Path propertyPath, CriteriaBuilder criteriaBuilder) {
    String value = (String) getArguments().get(0);
    Class<?> type = propertyPath.getJavaType();
    if (isNativeNumericType(type)) {
      return criteriaBuilder.le(propertyPath.as(Double.class), Double.valueOf(value));
    }
    if (type.isEnum()) {
      return criteriaBuilder.lessThanOrEqualTo(
          propertyPath, Enum.valueOf((Class<? extends Enum>) type, value));
    }
    return criteriaBuilder.le(propertyPath.as(BigDecimal.class), new BigDecimal(value));
  }
}
