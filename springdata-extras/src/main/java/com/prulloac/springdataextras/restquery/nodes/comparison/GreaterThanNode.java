package com.prulloac.springdataextras.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Collections;

/** @author Prulloac */
public class GreaterThanNode extends ComparisonNode {
  public GreaterThanNode(String field, String value) {
    super(field, Collections.singletonList(value));
  }

  @Override
  public Predicate getPredicate(Expression propertyPath, CriteriaBuilder criteriaBuilder) {
    String value = getArguments().get(0);
    return criteriaBuilder.greaterThan(propertyPath, value);
  }
}
