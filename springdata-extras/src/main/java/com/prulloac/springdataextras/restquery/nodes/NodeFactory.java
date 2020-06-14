package com.prulloac.springdataextras.restquery.nodes;

import com.google.re2j.Pattern;
import com.prulloac.springdataextras.restquery.nodes.comparison.DistinctNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.EqualsNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.GreaterThanNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.LessThanNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.NotNullNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.NullNode;
import com.prulloac.springdataextras.restquery.nodes.logical.AndNode;
import com.prulloac.springdataextras.restquery.nodes.logical.NotNode;
import com.prulloac.springdataextras.restquery.nodes.logical.OrNode;
import com.prulloac.springdataextras.restquery.operators.QueryOperator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.DISTINCT;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.EQUAL;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.GREATER_THAN;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.LESS_THAN;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.NOT_NULL;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.NULL;
import static com.prulloac.springdataextras.restquery.operators.LogicalOperator.AND;
import static com.prulloac.springdataextras.restquery.operators.LogicalOperator.NOT;
import static com.prulloac.springdataextras.restquery.operators.LogicalOperator.OR;

/** @author Prulloac */
public class NodeFactory {
  private static final Pattern REGEX_PATTERN_NOT_LOGICAL_NODE =
      Pattern.compile(NOT.getRegexForRepresentations());

  private NodeFactory() {
    super();
  }

  public static QueryNode getNode(String query) {
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
    if (hasOperatorExpression(query, DISTINCT)) {
      return createDistinctNode(query);
    }
    if (hasOperatorExpression(query, EQUAL)) {
      return createEqualNode(query);
    }
    if (hasOperatorExpression(query, NOT_NULL)) {
      return createNotNullNode(query);
    }
    if (hasOperatorExpression(query, NULL)) {
      return createNullNode(query);
    }
    return createNumericComparisonNode(query);
  }

  private static QueryNode createNumericComparisonNode(String query) {
    if (hasOperatorExpression(query, GREATER_THAN)) {
      return createGreaterThanNode(query);
    }
    if (hasOperatorExpression(query, LESS_THAN)) {
      return createLessThanNode(query);
    }
    return createStringComparisonNode(query);
  }

  private static QueryNode createStringComparisonNode(String query) {
    return createTimeAndDateComparisonNode(query);
  }

  private static QueryNode createTimeAndDateComparisonNode(String query) {
    return null;
  }

  private static QueryNode createOrNode(String query) {
    List<QueryNode> children =
        Arrays.stream(splitQueryForOperator(query, OR))
            .map(NodeFactory::getNode)
            .collect(Collectors.toList());
    return new OrNode(children);
  }

  private static QueryNode createAndNode(String query) {
    List<QueryNode> children =
        Arrays.stream(splitQueryForOperator(query, AND))
            .map(NodeFactory::getNode)
            .collect(Collectors.toList());
    return new AndNode(children);
  }

  private static QueryNode createNotNode(String query) {
    String[] parts = splitQueryForOperator(query, NOT);
    if (parts.length > 2) {
      throw new IllegalArgumentException();
    }
    String newQuery = REGEX_PATTERN_NOT_LOGICAL_NODE.matcher(query).replaceAll(" ");
    return new NotNode(Collections.singletonList(getNode(newQuery)));
  }

  private static QueryNode createDistinctNode(String query) {
    String[] parts = splitQueryForOperator(query, DISTINCT);
    if (parts.length != 2) {
      throw new IllegalArgumentException();
    }
    return new DistinctNode(parts[0], parts[1].split(","));
  }

  private static QueryNode createEqualNode(String query) {
    String[] parts = splitQueryForOperator(query, EQUAL);
    if (parts.length != 2) {
      throw new IllegalArgumentException();
    }
    return new EqualsNode(parts[0], parts[1].split(","));
  }

  private static QueryNode createNotNullNode(String query) {
    String[] parts = splitQueryForOperator(query, NOT_NULL);
    if (parts.length != 1) {
      throw new IllegalArgumentException();
    }
    return new NotNullNode(parts[0]);
  }

  private static QueryNode createNullNode(String query) {
    String[] parts = splitQueryForOperator(query, NULL);
    if (parts.length != 1) {
      throw new IllegalArgumentException();
    }
    return new NullNode(parts[0]);
  }

  private static QueryNode createGreaterThanNode(String query) {
    String[] parts = splitQueryForOperator(query, GREATER_THAN);
    if (parts.length != 2) {
      throw new IllegalArgumentException();
    }
    return new GreaterThanNode(parts[0], parts[1]);
  }

  private static QueryNode createLessThanNode(String query) {
    String[] parts = splitQueryForOperator(query, LESS_THAN);
    if (parts.length != 2) {
      throw new IllegalArgumentException();
    }
    return new LessThanNode(parts[0], parts[1]);
  }

  private static boolean hasOperatorExpression(String query, QueryOperator operator) {
    return Pattern.compile("(.*)(" + operator.getRegexForRepresentations() + ")(.*)")
        .matches(query);
  }

  private static String[] splitQueryForOperator(String query, QueryOperator operator) {
    return query.split(operator.getRegexForRepresentations());
  }
}
