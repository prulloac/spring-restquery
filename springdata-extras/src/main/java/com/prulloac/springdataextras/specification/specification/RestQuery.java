package com.prulloac.springdataextras.specification.specification;

import org.springframework.data.jpa.domain.Specification;

import com.prulloac.springdataextras.specification.nodes.NodeFactory;
import com.prulloac.springdataextras.specification.nodes.QueryNode;
import com.prulloac.springdataextras.specification.nodes.comparison.ComparisonNode;
import com.prulloac.springdataextras.specification.nodes.logical.LogicalNode;
import com.prulloac.springdataextras.specification.operators.LogicalOperator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ManagedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.prulloac.springdataextras.specification.operators.LogicalOperator.AND;
import static com.prulloac.springdataextras.specification.operators.LogicalOperator.NOT;
import static com.prulloac.springdataextras.specification.operators.LogicalOperator.OR;

public class RestQuery<T> implements Specification<T> {

  private final QueryNode baseNode;

  public RestQuery(String query) {
    this.baseNode = NodeFactory.getNode(query);
  }

  public QueryNode getQueryNode() {
    return baseNode;
  }

  private Predicate createPredicate(
      QueryNode node, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    if (node instanceof LogicalNode) {
      return createLogicalPredicate((LogicalNode) node, root, query, criteriaBuilder);
    } else if (node instanceof ComparisonNode) {
      return createComparisonPredicate((ComparisonNode) node, root, query, criteriaBuilder);
    }
    throw new IllegalArgumentException();
  }

  private Predicate createLogicalPredicate(
      LogicalNode node, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();
    node.forEach(child -> predicates.add(createPredicate(child, root, query, criteriaBuilder)));
    LogicalOperator operator = node.getOperator();
    int size = predicates.size();
    if (AND.equals(operator)) {
      return criteriaBuilder.and(predicates.toArray(new Predicate[size]));
    } else if (OR.equals(operator)) {
      return criteriaBuilder.or(predicates.toArray(new Predicate[size]));
    } else if (NOT.equals(operator)) {
      return criteriaBuilder.not(predicates.get(0));
    }
    throw new IllegalArgumentException();
  }

  public Predicate createComparisonPredicate(
      ComparisonNode node, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<String> values = node.getArguments();
    String field = node.getField();
    Expression propertyPath = findPropertyPath(field, root);
    return node.getPredicate(propertyPath, criteriaBuilder, values);
  }

  private Path<?> findPropertyPath(String field, Root<T> root) {
    String[] fieldGraph = field.split("\\.");
    Path<?> base = root;
    ManagedType<?> baseType = root.getModel();
    for (String node : fieldGraph) {
      Set<Attribute<?, ?>> attributes = (Set<Attribute<?, ?>>) baseType.getAttributes();
      if (attributes.stream().map(Attribute::getName).anyMatch(node::equals)) {
        Attribute attribute = baseType.getAttribute(node);
        if (attribute.isAssociation()) {
          baseType = attribute.getDeclaringType();
          if (base instanceof Join) {
            base = base.get(node);
          } else {
            base = ((From) base).join(node);
          }
        } else {
          base = base.get(node);
        }
      }
    }
    return base;
  }

  @Override
  public Predicate toPredicate(
      Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    return createPredicate(baseNode, root, query, criteriaBuilder);
  }
}
