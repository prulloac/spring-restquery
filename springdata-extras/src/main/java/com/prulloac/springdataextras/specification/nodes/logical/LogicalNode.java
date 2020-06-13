package com.prulloac.springdataextras.specification.nodes.logical;

import com.prulloac.springdataextras.specification.nodes.QueryNode;
import com.prulloac.springdataextras.specification.operators.LogicalOperator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

/** @author Prulloac */
public abstract class LogicalNode implements QueryNode, Iterable<QueryNode> {

  protected final List<QueryNode> children;

  protected LogicalNode(List<? extends QueryNode> children) {
    if (isEmpty(children)) {
      throw new IllegalArgumentException();
    }
    this.children = Collections.unmodifiableList(children);
  }

  @Override
  public Iterator<QueryNode> iterator() {
    return children.iterator();
  }

  public List<QueryNode> getChildren() {
    return children;
  }

  @Override
  public abstract LogicalOperator getOperator();
}
