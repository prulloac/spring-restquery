package com.prulloac.springdataextras.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Collections;

/** @author Prulloac */
public class NullNode extends ComparisonNode {
  public NullNode(String field) {
    super(field, Collections.emptyList());
  }

  @Override
  public Predicate getPredicate(Expression propertyPath, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.isNull(propertyPath);
  }
}
