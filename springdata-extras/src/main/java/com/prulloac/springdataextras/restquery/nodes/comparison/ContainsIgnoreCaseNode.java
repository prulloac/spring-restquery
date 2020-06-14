package com.prulloac.springdataextras.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/** @author Prulloac */
public class ContainsIgnoreCaseNode extends ComparisonNode {
  public ContainsIgnoreCaseNode(String field, String value) {
    super(field, value);
  }

  @Override
  public Predicate getPredicate(Path propertyPath, CriteriaBuilder criteriaBuilder) {
    String value = (String) getArguments().get(0);
    return criteriaBuilder.like(
        criteriaBuilder.upper(propertyPath), "%" + value.toUpperCase() + "%");
  }
}
