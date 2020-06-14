package com.prulloac.springdataextras.restquery.specification;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.prulloac.springdataextras.restquery.nodes.NodeFactory;
import com.prulloac.springdataextras.restquery.nodes.QueryNode;
import com.prulloac.springdataextras.restquery.nodes.comparison.ComparisonNode;
import com.prulloac.springdataextras.restquery.nodes.logical.LogicalNode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ManagedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/** @author Prulloac */
public class RestQuery<T> implements Specification<T> {
  private static final LoadingCache<String, RestQuery<?>> CACHE =
      CacheBuilder.newBuilder()
          .expireAfterAccess(10, TimeUnit.MINUTES)
          .build(
              new CacheLoader<>() {
                @Override
                public RestQuery<?> load(String key) {
                  String query = key.split(" \\* ")[1];
                  return new RestQuery<>(query);
                }
              });

  private final transient QueryNode baseNode;

  private RestQuery(String query) {
    this.baseNode = NodeFactory.getNode(query);
  }

  public static <T> RestQuery<T> buildQuery(Class<T> tClass, String query) {
    String cacheKey = tClass.getSimpleName() + " * " + query;
    try {
      return (RestQuery<T>) CACHE.get(cacheKey);
    } catch (ExecutionException e) {
      throw new IllegalArgumentException();
    }
  }

  private Predicate createPredicate(
      QueryNode node, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    if (node instanceof LogicalNode) {
      return createLogicalPredicate((LogicalNode) node, root, query, criteriaBuilder);
    } else if (node instanceof ComparisonNode) {
      return createComparisonPredicate((ComparisonNode) node, root, criteriaBuilder);
    }
    throw new IllegalArgumentException();
  }

  private Predicate createLogicalPredicate(
      LogicalNode node, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();
    node.forEach(child -> predicates.add(createPredicate(child, root, query, criteriaBuilder)));
    return node.getPredicate(criteriaBuilder, predicates);
  }

  public Predicate createComparisonPredicate(
      ComparisonNode node, Root<T> root, CriteriaBuilder criteriaBuilder) {
    String field = node.getField();
    Expression<?> propertyPath = findPropertyPath(field, root);
    return node.getPredicate(propertyPath, criteriaBuilder);
  }

  private Path<?> findPropertyPath(String field, Root<T> root) {
    String[] fieldGraph = field.split("\\.");
    Path<?> base = root;
    ManagedType<?> baseType = root.getModel();
    for (String node : fieldGraph) {
      Set<Attribute<?, ?>> attributes = (Set<Attribute<?, ?>>) baseType.getAttributes();
      if (attributes.stream().map(Attribute::getName).anyMatch(node::equals)) {
        Attribute<?, ?> attribute = baseType.getAttribute(node);
        if (attribute.isAssociation()) {
          baseType = attribute.getDeclaringType();
          if (base instanceof Join) {
            base = base.get(node);
          } else {
            base = ((From) base).join(node, JoinType.LEFT);
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
