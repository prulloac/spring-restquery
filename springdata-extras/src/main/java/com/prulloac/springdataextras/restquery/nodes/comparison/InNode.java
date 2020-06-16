package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.google.common.base.Strings;
import com.google.re2j.Pattern;
import com.prulloac.springdataextras.errors.RestQueryException;
import com.prulloac.springdataextras.errors.RestQueryNodeCreationException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.prulloac.springdataextras.restquery.nodes.comparison.NumericComparisonNode.isNativeNumericType;

public class InNode extends ComparisonNode {
  private static final Pattern LIST_PATTERN = Pattern.compile("^(\\()((.+)|(.+)(,.*))(\\))$");

  public InNode(String field, String value) {
    super(field, value);
  }

  public static void assertValuesSyntax(String original) {
    if (!LIST_PATTERN.matches(original)) {
      throw new RestQueryNodeCreationException();
    }
  }

  @Override
  public Predicate getPredicate(Path<?> propertyPath, CriteriaBuilder criteriaBuilder) {
    List<String> values = splitValues();
    Class<?> javaType = propertyPath.getJavaType();
    if (isNativeNumericType(javaType)) {
      CriteriaBuilder.In<Number> in = criteriaBuilder.in(propertyPath.as(Double.class));
      for (String value : values) {
        in.value(Double.valueOf(value));
      }
      return in;
    } else if (javaType.equals(String.class)) {
      CriteriaBuilder.In<String> in = criteriaBuilder.in(propertyPath.as(String.class));
      for (String value : values) {
        in.value(value);
      }
      return in;
    }
    throw new RestQueryException();
  }

  private List<String> splitValues() {
    String original = (String) getArguments().get(0);
    int length = original.length();
    String withoutParenthesis = original.substring(1, length - 1);
    java.util.function.Predicate<String> notNullNorEmpty = string -> !Strings.isNullOrEmpty(string);
    return Arrays.stream(withoutParenthesis.split(","))
        .filter(notNullNorEmpty)
        .collect(Collectors.toList());
  }
}
