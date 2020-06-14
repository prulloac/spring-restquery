package com.prulloac.springdataextras.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.Collections;

/** @author Prulloac */
public class GreaterThanEqualsNode extends ComparisonNode {
  public GreaterThanEqualsNode(String field, String value) {
    super(field, value);
  }

  @Override
  public Predicate getPredicate(Path propertyPath, CriteriaBuilder criteriaBuilder) {
    String value = getArguments().get(0);
    Class<?> type = propertyPath.getJavaType();
    if (type.equals(Integer.class) || type.equals(int.class)) {
      return criteriaBuilder.ge(propertyPath.as(Integer.class), Integer.valueOf(value));
    }
    if (type.equals(Long.class) || type.equals(long.class)) {
      return criteriaBuilder.ge(propertyPath.as(Long.class), Long.valueOf(value));
    }
    if (type.equals(Double.class) || type.equals(double.class)) {
      return criteriaBuilder.ge(propertyPath.as(Double.class), Double.valueOf(value));
    }
    if (type.equals(Float.class) || type.equals(float.class)) {
      return criteriaBuilder.ge(propertyPath.as(Float.class), Float.valueOf(value));
    }
    if (type.equals(Short.class) || type.equals(short.class)) {
      return criteriaBuilder.ge(propertyPath.as(Short.class), Short.valueOf(value));
    }
    if (type.isEnum()) {
      return criteriaBuilder.greaterThanOrEqualTo(
          propertyPath.as(Enum.class), Enum.valueOf((Class<Enum>) type, value));
    }
    return criteriaBuilder.ge(propertyPath.as(BigDecimal.class), new BigDecimal(value));
  }
}
