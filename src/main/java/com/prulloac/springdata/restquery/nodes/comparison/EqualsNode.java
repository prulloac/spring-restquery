package com.prulloac.springdata.restquery.nodes.comparison;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;

/** @author Prulloac */
public class EqualsNode extends ComparisonNode {
  public EqualsNode(String field, String[] arguments) {
    super(field, Arrays.asList(arguments));
  }

  @Override
  public Predicate getPredicate(Path<?> propertyPath, CriteriaBuilder criteriaBuilder) {
    List<Object> arguments = getArguments();
    Predicate base =
        criteriaBuilder.equal(propertyPath, toEnumIfNeeded(propertyPath).apply(arguments.get(0)));
    for (int i = 1; i < arguments.size(); i++) {
      Predicate otherCondition =
          criteriaBuilder.equal(propertyPath, toEnumIfNeeded(propertyPath).apply(arguments.get(i)));
      base = criteriaBuilder.or(base, otherCondition);
    }
    return base;
  }
}
