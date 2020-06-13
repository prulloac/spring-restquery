package com.prulloac.springdataextras.specification.nodes.comparison;

import com.prulloac.springdataextras.specification.operators.ComparisonOperator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Collections;
import java.util.List;

public class NotNullNode extends ComparisonNode {
  protected NotNullNode(String field, List<String> arguments) {
    super(field, arguments);
  }

  public NotNullNode(String field) {
    this(field, Collections.emptyList());
  }

  @Override
  public Predicate getPredicate(
      Expression propertyPath, CriteriaBuilder criteriaBuilder, List<String> values) {
    return criteriaBuilder.isNotNull(propertyPath);
  }

  @Override
  public ComparisonOperator getOperator() {
    return ComparisonOperator.NOT_NULL;
  }
}
