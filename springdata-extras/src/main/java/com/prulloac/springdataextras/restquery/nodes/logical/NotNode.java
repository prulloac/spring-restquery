package com.prulloac.springdataextras.restquery.nodes.logical;

import com.prulloac.springdataextras.restquery.nodes.QueryNode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.List;

/** @author Prulloac */
public class NotNode extends LogicalNode {
  public NotNode(List<? extends QueryNode> children) {
    super(children);
  }

  @Override
  public Predicate getPredicate(CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
    return criteriaBuilder.not(predicates.get(0));
  }
}
