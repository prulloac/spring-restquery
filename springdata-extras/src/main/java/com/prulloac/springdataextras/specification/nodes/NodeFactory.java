package com.prulloac.springdataextras.specification.nodes;

import com.google.re2j.Pattern;
import com.prulloac.springdataextras.specification.nodes.comparison.DistinctNode;
import com.prulloac.springdataextras.specification.nodes.comparison.EqualsNode;
import com.prulloac.springdataextras.specification.nodes.comparison.NotNullNode;
import com.prulloac.springdataextras.specification.nodes.comparison.NullNode;
import com.prulloac.springdataextras.specification.nodes.logical.AndNode;
import com.prulloac.springdataextras.specification.nodes.logical.NotNode;
import com.prulloac.springdataextras.specification.nodes.logical.OrNode;
import com.prulloac.springdataextras.specification.operators.QueryOperator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.prulloac.springdataextras.specification.operators.ComparisonOperator.DISTINCT;
import static com.prulloac.springdataextras.specification.operators.ComparisonOperator.EQUAL;
import static com.prulloac.springdataextras.specification.operators.ComparisonOperator.NOT_NULL;
import static com.prulloac.springdataextras.specification.operators.ComparisonOperator.NULL;
import static com.prulloac.springdataextras.specification.operators.LogicalOperator.AND;
import static com.prulloac.springdataextras.specification.operators.LogicalOperator.NOT;
import static com.prulloac.springdataextras.specification.operators.LogicalOperator.OR;
import static com.prulloac.springdataextras.utils.Constants.REGEX_PATTERN_NOT_LOGICAL_NODE;

/** @author Prulloac */
public interface NodeFactory {

  static QueryNode getNode(String query) {
    if (hasOperatorExpression(query, OR)) {
      return createOrNode(query.trim());
    }
    if (hasOperatorExpression(query, AND)) {
      return createAndNode(query.trim());
    }
    if (hasOperatorExpression(query, NOT)) {
      return createNotNode(query.trim());
    }
    return createComparisonNode(query.trim());
  }

  private static QueryNode createComparisonNode(String query) {
    String[] parts;
    if (hasOperatorExpression(query, DISTINCT)) {
      parts = query.split(DISTINCT.getRegexForRepresentations());
      if (parts.length != 2) {
        throw new IllegalArgumentException();
      }
      return new DistinctNode(parts[0], Arrays.asList(parts[1].split(",")));
    }
    if (hasOperatorExpression(query, EQUAL)) {
      parts = query.split(EQUAL.getRegexForRepresentations());
      if (parts.length != 2) {
        throw new IllegalArgumentException();
      }
      return new EqualsNode(parts[0], Arrays.asList(parts[1].split(",")));
    }
    if (hasOperatorExpression(query, NULL)) {
      parts = query.split(NULL.getRegexForRepresentations());
      if (parts.length != 1) {
        throw new IllegalArgumentException();
      }
      return new NullNode(parts[0]);
    }
    if (hasOperatorExpression(query, NOT_NULL)) {
      parts = query.split(NOT_NULL.getRegexForRepresentations());
      if (parts.length != 1) {
        throw new IllegalArgumentException();
      }
      return new NotNullNode(parts[0]);
    }
    throw new IllegalArgumentException();
  }

  private static QueryNode createNotNode(String query) {
    String[] parts = query.split(NOT.getRegexForRepresentations());
    if (parts.length > 2) {
      throw new IllegalArgumentException();
    }
    String newQuery = REGEX_PATTERN_NOT_LOGICAL_NODE.matcher(query).replaceAll(" ");
    return new NotNode(Collections.singletonList(getNode(newQuery)));
  }

  private static QueryNode createAndNode(String query) {
    List<QueryNode> children =
        Arrays.stream(query.split(AND.getRegexForRepresentations()))
            .map(NodeFactory::getNode)
            .collect(Collectors.toList());
    return new AndNode(children);
  }

  private static QueryNode createOrNode(String query) {
    List<QueryNode> children =
        Arrays.stream(query.split(OR.getRegexForRepresentations()))
            .map(NodeFactory::getNode)
            .collect(Collectors.toList());
    return new OrNode(children);
  }

  private static boolean hasOperatorExpression(String query, QueryOperator operator) {
    return Pattern.compile("(.*)(" + operator.getRegexForRepresentations() + ")(.*)")
        .matches(query);
  }
}
