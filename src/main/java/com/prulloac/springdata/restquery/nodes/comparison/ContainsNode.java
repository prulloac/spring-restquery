package com.prulloac.springdata.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/** @author Prulloac */
public class ContainsNode extends ComparisonNode {
  public ContainsNode(String field, String value) {
    super(field, value);
  }

  @Override
  public Predicate getPredicate(Path propertyPath, CriteriaBuilder criteriaBuilder) {
    String value = (String) getArguments().get(0);
    return criteriaBuilder.like(propertyPath, "%" + value + "%");
  }
}
