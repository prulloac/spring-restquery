package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.prulloac.springdataextras.restquery.nodes.QueryNode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Collections;
import java.util.List;

/** @author Prulloac */
public abstract class ComparisonNode implements QueryNode {
  private final String field;
  private final List<Object> arguments;

  protected ComparisonNode(String field, List<Object> arguments) {
    this.field = field;
    this.arguments = arguments;
  }

  protected ComparisonNode(String field, Object value) {
    this(field, Collections.singletonList(value));
  }

  public String getField() {
    return field;
  }

  public List<Object> getArguments() {
    return arguments;
  }

  public abstract Predicate getPredicate(Path<?> propertyPath, CriteriaBuilder criteriaBuilder);

  protected boolean isNativeNumericType(Class<?> type) {
    if (type.equals(Integer.class) || type.equals(int.class)) {
      return true;
    }
    if (type.equals(Long.class) || type.equals(long.class)) {
      return true;
    }
    if (type.equals(Double.class) || type.equals(double.class)) {
      return true;
    }
    if (type.equals(Float.class) || type.equals(float.class)) {
      return true;
    }
    return type.equals(Short.class) || type.equals(short.class);
  }
}
