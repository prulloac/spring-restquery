package com.prulloac.springdataextras.specification.nodes.comparison;

import com.prulloac.springdataextras.specification.operators.ComparisonOperator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class EqualsNode extends ComparisonNode {

  public EqualsNode(String field, List<String> arguments) {
    super(field, arguments);
  }

  @Override
  public Predicate getPredicate(
      Expression propertyPath, CriteriaBuilder criteriaBuilder, List<String> values) {
    Predicate base = criteriaBuilder.equal(propertyPath, values.get(0));
    for (int i = 1; i < values.size(); i++) {
      Predicate otherCondition = criteriaBuilder.equal(propertyPath, values.get(i));
      base = criteriaBuilder.or(base, otherCondition);
    }
    return base;
  }

  @Override
  public ComparisonOperator getOperator() {
    return ComparisonOperator.EQUAL;
  }
}
