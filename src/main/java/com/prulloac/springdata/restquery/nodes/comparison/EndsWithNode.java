package com.prulloac.springdata.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/** @author Prulloac */
public class EndsWithNode extends ComparisonNode {
  public EndsWithNode(String field, String value) {
    super(field, value);
  }

  @Override
  public Predicate getPredicate(Path<?> propertyPath, CriteriaBuilder criteriaBuilder) {
    String value = (String) getArguments().get(0);
    return criteriaBuilder.like(propertyPath.as(String.class), "%" + value);
  }
}
