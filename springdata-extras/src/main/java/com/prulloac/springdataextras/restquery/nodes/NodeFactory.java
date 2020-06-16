package com.prulloac.springdataextras.restquery.nodes;

import com.google.re2j.Pattern;
import com.prulloac.springdataextras.errors.RestQueryNodeCreationException;
import com.prulloac.springdataextras.errors.RestQueryUnidentifiedOperationException;
import com.prulloac.springdataextras.restquery.nodes.comparison.AfterNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.BeforeNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.BetweenNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.ContainsIgnoreCaseNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.ContainsNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.DateTimeComparisonNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.DistinctNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.EndsWithIgnoreCaseNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.EndsWithNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.EqualsIgnoreCaseNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.EqualsNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.GreaterThanEqualsNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.GreaterThanNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.InNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.LessThanEqualsNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.LessThanNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.NotNullNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.NullNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.StartsWithIgnoreCaseNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.StartsWithNode;
import com.prulloac.springdataextras.restquery.nodes.logical.AndNode;
import com.prulloac.springdataextras.restquery.nodes.logical.NotNode;
import com.prulloac.springdataextras.restquery.nodes.logical.OrNode;
import com.prulloac.springdataextras.restquery.operators.QueryOperator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.AFTER;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.BEFORE;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.BETWEEN;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.CONTAINS;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.CONTAINS_IGNORE_CASE;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.DISTINCT;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.ENDS_WITH;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.ENDS_WITH_IGNORE_CASE;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.EQUALS;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.EQUALS_IGNORE_CASE;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.GREATER_THAN;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.GREATER_THAN_EQUALS;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.IN;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.LESS_THAN;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.LESS_THAN_EQUALS;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.NOT_NULL;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.NULL;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.STARTS_WITH;
import static com.prulloac.springdataextras.restquery.operators.ComparisonOperator.STARTS_WITH_IGNORE_CASE;
import static com.prulloac.springdataextras.restquery.operators.LogicalOperator.AND;
import static com.prulloac.springdataextras.restquery.operators.LogicalOperator.NOT;
import static com.prulloac.springdataextras.restquery.operators.LogicalOperator.OR;

/** @author Prulloac */
public class NodeFactory {

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
    if (hasOperatorExpression(query, GREATER_THAN_EQUALS)) {
      return createGreaterThanEqualsNode(query);
    }
    if (hasOperatorExpression(query, LESS_THAN_EQUALS)) {
      return createLessThanEqualsNode(query);
    }
    if (hasOperatorExpression(query, GREATER_THAN)) {
      return createGreaterThanNode(query);
    }
    if (hasOperatorExpression(query, LESS_THAN)) {
      return createLessThanNode(query);
    }
    if (hasOperatorExpression(query, EQUALS)) {
      return createEqualNode(query);
    }
    if (hasOperatorExpression(query, IN)) {
      return createInNode(query);
    }
    return createNullComparisonNode(query);
  }

  private static QueryNode createNullComparisonNode(String query) {
    if (hasOperatorExpression(query, NOT_NULL)) {
      return createNotNullNode(query);
    }
    if (hasOperatorExpression(query, NULL)) {
      return createNullNode(query);
    }
    return createStringComparisonNode(query);
  }

  private static QueryNode createStringComparisonNode(String query) {
    if (hasOperatorExpression(query, EQUALS_IGNORE_CASE)) {
      return createEqualsIgnoreCaseNode(query);
    }
    if (hasOperatorExpression(query, STARTS_WITH_IGNORE_CASE)) {
      return createStartsWithIgnoreCaseNode(query);
    }
    if (hasOperatorExpression(query, CONTAINS_IGNORE_CASE)) {
      return createContainsIgnoreCaseNode(query);
    }
    if (hasOperatorExpression(query, ENDS_WITH_IGNORE_CASE)) {
      return createEndsWithIgnoreCaseNode(query);
    }
    if (hasOperatorExpression(query, STARTS_WITH)) {
      return createStartsWithNode(query);
    }
    if (hasOperatorExpression(query, CONTAINS)) {
      return createContainsNode(query);
    }
    if (hasOperatorExpression(query, ENDS_WITH)) {
      return createEndsWithNode(query);
    }
    return createDatetimeComparisonNode(query);
  }

  private static QueryNode createDatetimeComparisonNode(String query) {
    if (hasOperatorExpression(query, BEFORE)) {
      return createBeforeNode(query);
    }
    if (hasOperatorExpression(query, AFTER)) {
      return createAfterNode(query);
    }
    if (hasOperatorExpression(query, BETWEEN)) {
      return createBetweenNode(query);
    }
    throw new RestQueryUnidentifiedOperationException();
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
    assertPartsLength(parts, 2);
    String newQuery = NotNode.reformatQuery(query);
    return new NotNode(getNode(newQuery));
  }

  private static QueryNode createDistinctNode(String query) {
    String[] parts = splitQueryForOperator(query, DISTINCT);
    assertPartsLength(parts, 2);
    return new DistinctNode(parts[0], parts[1].split(","));
  }

  private static QueryNode createEqualNode(String query) {
    String[] parts = splitQueryForOperator(query, EQUALS);
    assertPartsLength(parts, 2);
    return new EqualsNode(parts[0], parts[1].split(","));
  }

  private static QueryNode createInNode(String query) {
    String[] parts = splitQueryForOperator(query, IN);
    assertPartsLength(parts, 2);
    InNode.assertValuesSyntax(parts[1]);
    return new InNode(parts[0], parts[1]);
  }

  private static QueryNode createNotNullNode(String query) {
    String[] parts = splitQueryForOperator(query, NOT_NULL);
    assertPartsLength(parts, 1);
    return new NotNullNode(parts[0]);
  }

  private static QueryNode createNullNode(String query) {
    String[] parts = splitQueryForOperator(query, NULL);
    assertPartsLength(parts, 1);
    return new NullNode(parts[0]);
  }

  private static QueryNode createGreaterThanNode(String query) {
    String[] parts = splitQueryForOperator(query, GREATER_THAN);
    assertPartsLength(parts, 2);
    return new GreaterThanNode(parts[0], parts[1]);
  }

  private static QueryNode createLessThanNode(String query) {
    String[] parts = splitQueryForOperator(query, LESS_THAN);
    assertPartsLength(parts, 2);
    return new LessThanNode(parts[0], parts[1]);
  }

  private static QueryNode createEqualsIgnoreCaseNode(String query) {
    String[] parts = splitQueryForOperator(query, EQUALS_IGNORE_CASE);
    assertPartsLength(parts, 2);
    return new EqualsIgnoreCaseNode(parts[0], parts[1]);
  }

  private static QueryNode createGreaterThanEqualsNode(String query) {
    String[] parts = splitQueryForOperator(query, GREATER_THAN_EQUALS);
    assertPartsLength(parts, 2);
    return new GreaterThanEqualsNode(parts[0], parts[1]);
  }

  private static QueryNode createLessThanEqualsNode(String query) {
    String[] parts = splitQueryForOperator(query, LESS_THAN_EQUALS);
    assertPartsLength(parts, 2);
    return new LessThanEqualsNode(parts[0], parts[1]);
  }

  private static QueryNode createStartsWithNode(String query) {
    String[] parts = splitQueryForOperator(query, STARTS_WITH);
    assertPartsLength(parts, 2);
    return new StartsWithNode(parts[0], parts[1]);
  }

  private static QueryNode createStartsWithIgnoreCaseNode(String query) {
    String[] parts = splitQueryForOperator(query, STARTS_WITH_IGNORE_CASE);
    assertPartsLength(parts, 2);
    return new StartsWithIgnoreCaseNode(parts[0], parts[1]);
  }

  private static QueryNode createContainsNode(String query) {
    String[] parts = splitQueryForOperator(query, CONTAINS);
    assertPartsLength(parts, 2);
    return new ContainsNode(parts[0], parts[1]);
  }

  private static QueryNode createContainsIgnoreCaseNode(String query) {
    String[] parts = splitQueryForOperator(query, CONTAINS_IGNORE_CASE);
    assertPartsLength(parts, 2);
    return new ContainsIgnoreCaseNode(parts[0], parts[1]);
  }

  private static QueryNode createEndsWithNode(String query) {
    String[] parts = splitQueryForOperator(query, ENDS_WITH);
    assertPartsLength(parts, 2);
    return new EndsWithNode(parts[0], parts[1]);
  }

  private static QueryNode createEndsWithIgnoreCaseNode(String query) {
    String[] parts = splitQueryForOperator(query, ENDS_WITH_IGNORE_CASE);
    assertPartsLength(parts, 2);
    return new EndsWithIgnoreCaseNode(parts[0], parts[1]);
  }

  private static QueryNode createBeforeNode(String query) {
    String[] parts = splitQueryForOperator(query, BEFORE);
    assertPartsLength(parts, 2);
    DateTimeComparisonNode.assertValueSyntax(parts[1]);
    return new BeforeNode(parts[0], parts[1]);
  }

  private static QueryNode createAfterNode(String query) {
    String[] parts = splitQueryForOperator(query, AFTER);
    assertPartsLength(parts, 2);
    DateTimeComparisonNode.assertValueSyntax(parts[1]);
    return new AfterNode(parts[0], parts[1]);
  }

  private static QueryNode createBetweenNode(String query) {
    String[] parts = splitQueryForOperator(query, BETWEEN);
    assertPartsLength(parts, 2);
    BetweenNode.assertValueSyntax(parts[1]);
    return new BetweenNode(parts[0], parts[1]);
  }

  private static boolean hasOperatorExpression(String query, QueryOperator operator) {
    return Pattern.compile("(.*)(" + operator.getRegexForRepresentations() + ")(.*)")
        .matches(query);
  }

  private static String[] splitQueryForOperator(String query, QueryOperator operator) {
    return query.split(operator.getRegexForRepresentations());
  }

  private static void assertPartsLength(String[] parts, int expectedLength) {
    if (parts.length != expectedLength) {
      throw new RestQueryNodeCreationException();
    }
  }
}
