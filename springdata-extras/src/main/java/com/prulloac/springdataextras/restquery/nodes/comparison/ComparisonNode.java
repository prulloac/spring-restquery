package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.prulloac.springdataextras.restquery.nodes.QueryNode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

/** @author Prulloac */
public abstract class ComparisonNode implements QueryNode {
  private final String field;
  private final List<String> arguments;

  protected ComparisonNode(String field, List<String> arguments) {
    this.field = field;
    this.arguments = arguments;
  }

  public String getField() {
    return field;
  }

  public List<String> getArguments() {
    return arguments;
  }

  public abstract Predicate getPredicate(Expression propertyPath, CriteriaBuilder criteriaBuilder);
}
