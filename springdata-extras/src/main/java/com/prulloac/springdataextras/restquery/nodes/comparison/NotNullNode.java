package com.prulloac.springdataextras.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Collections;

/** @author Prulloac */
public class NotNullNode extends ComparisonNode {
  public NotNullNode(String field) {
    super(field, Collections.emptyList());
  }

  @Override
  public Predicate getPredicate(Expression propertyPath, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.isNotNull(propertyPath);
  }
}
