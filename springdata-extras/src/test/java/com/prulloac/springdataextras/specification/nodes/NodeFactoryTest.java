package com.prulloac.springdataextras.specification.nodes;

import com.prulloac.springdataextras.specification.nodes.comparison.ComparisonNode;
import com.prulloac.springdataextras.specification.nodes.logical.LogicalNode;
import com.prulloac.springdataextras.specification.operators.ComparisonOperator;
import com.prulloac.springdataextras.specification.operators.LogicalOperator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

class NodeFactoryTest {

  @Test
  void getNode() {
    String query1 = "name='Adrian','Luke'";
    String query2 = "name='Adrian' and lastName='Smith'";
    String query3 = "name='Adrian' or lastName='Smith'";
    String query4 = "name='Adrian' && lastName!='Smith'";
    String query5 = "name!=test";
    QueryNode queryNode1 = NodeFactory.getNode(query1);
    assertThat(queryNode1, isA(ComparisonNode.class));
    QueryNode queryNode2 = NodeFactory.getNode(query2);
    assertThat(queryNode2, isA(LogicalNode.class));
    assertThat(queryNode2.getOperator(), is(LogicalOperator.AND));
    QueryNode queryNode3 = NodeFactory.getNode(query3);
    assertThat(queryNode3, isA(LogicalNode.class));
    assertThat(queryNode3.getOperator(), is(LogicalOperator.OR));
    QueryNode queryNode4 = NodeFactory.getNode(query4);
    assertThat(queryNode4, isA(LogicalNode.class));
    assertThat(queryNode4.getOperator(), is(LogicalOperator.AND));
    List<QueryNode> queryNode4Children = ((LogicalNode) queryNode4).getChildren();
    assertThat(queryNode4Children, hasItem(isA(ComparisonNode.class)));
    assertThat(
        queryNode4Children.stream().map(QueryNode::getOperator).collect(Collectors.toList()),
        hasItem(ComparisonOperator.DISTINCT));
    QueryNode queryNode5 = NodeFactory.getNode(query5);
    assertThat(queryNode5, isA(ComparisonNode.class));
    assertThat(queryNode5.getOperator(), is(ComparisonOperator.DISTINCT));
  }
}
