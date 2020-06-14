package com.prulloac.springdataextras.restquery.nodes;

import com.prulloac.springdataextras.restquery.nodes.comparison.ComparisonNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.DistinctNode;
import com.prulloac.springdataextras.restquery.nodes.logical.AndNode;
import com.prulloac.springdataextras.restquery.nodes.logical.LogicalNode;
import com.prulloac.springdataextras.restquery.nodes.logical.NotNode;
import com.prulloac.springdataextras.restquery.nodes.logical.OrNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.isA;

class NodeFactoryTest {

  @Test
  void getNode() {
    String query1 = "name='Adrian','Luke'";
    String query2 = "name='Adrian' and lastName='Smith'";
    String query3 = "name='Adrian' or lastName='Smith'";
    String query4 = "name='Adrian' && lastName!='Smith'";
    String query5 = "name not equals test";
    QueryNode queryNode1 = NodeFactory.getNode(query1);
    assertThat(queryNode1, isA(ComparisonNode.class));
    QueryNode queryNode2 = NodeFactory.getNode(query2);
    assertThat(queryNode2, isA(AndNode.class));
    QueryNode queryNode3 = NodeFactory.getNode(query3);
    assertThat(queryNode3, isA(OrNode.class));
    QueryNode queryNode4 = NodeFactory.getNode(query4);
    assertThat(queryNode4, isA(AndNode.class));
    List<QueryNode> queryNode4Children = ((LogicalNode) queryNode4).getChildren();
    assertThat(queryNode4Children, hasItem(isA(ComparisonNode.class)));
    assertThat(queryNode4Children, hasItem(isA(DistinctNode.class)));
    QueryNode queryNode5 = NodeFactory.getNode(query5);
    assertThat(queryNode5, isA(NotNode.class));
  }
}
