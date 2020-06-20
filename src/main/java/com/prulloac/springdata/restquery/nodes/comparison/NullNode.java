package com.prulloac.springdata.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Collections;

/** @author Prulloac */
public class NullNode extends ComparisonNode {
  public NullNode(String field) {
    super(field, Collections.emptyList());
  }

  @Override
  public Predicate getPredicate(Path<?> propertyPath, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.isNull(propertyPath);
  }
}
