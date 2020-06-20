package com.prulloac.springdata.restquery.nodes.logical;

import com.google.re2j.Pattern;
import com.prulloac.springdata.restquery.nodes.QueryNode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.Collections;
import java.util.List;

import static com.prulloac.springdata.restquery.operators.LogicalOperator.NOT;

/** @author Prulloac */
public class NotNode extends LogicalNode {
  private static final Pattern REGEX_PATTERN_NOT_LOGICAL_NODE =
      Pattern.compile(NOT.getRegexForRepresentations());

  public NotNode(QueryNode child) {
    super(Collections.singletonList(child));
  }

  public static String reformatQuery(String query) {
    return REGEX_PATTERN_NOT_LOGICAL_NODE.matcher(query).replaceAll(" ");
  }

  @Override
  public Predicate getPredicate(CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
    return criteriaBuilder.not(predicates.get(0));
  }
}
