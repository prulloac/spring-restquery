package com.prulloac.springdataextras.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;

/** @author Prulloac */
public class LessThanNode extends NumericComparisonNode {
  public LessThanNode(String field, Object value) {
    super(field, value);
  }

  @Override
  public Predicate getPredicate(Path propertyPath, CriteriaBuilder criteriaBuilder) {
    String value = (String) getArguments().get(0);
    Class<?> type = propertyPath.getJavaType();
    if (isNativeNumericType(type)) {
      return criteriaBuilder.lt(propertyPath.as(Double.class), Double.valueOf(value));
    }
    if (type.isEnum()) {
      return criteriaBuilder.lessThan(
          propertyPath.as(Enum.class), Enum.valueOf((Class<? extends Enum>) type, value));
    }
    return criteriaBuilder.lt(propertyPath.as(BigDecimal.class), new BigDecimal(value));
  }
}
