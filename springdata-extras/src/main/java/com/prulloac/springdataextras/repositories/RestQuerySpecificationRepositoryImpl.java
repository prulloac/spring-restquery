package com.prulloac.springdataextras.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.stream.Stream;

public class RestQuerySpecificationRepositoryImpl<T, K extends Serializable>
    extends SimpleJpaRepository<T, K> implements RestQuerySpecificationRepository<T, K> {

  private final EntityManager entityManager;

  public RestQuerySpecificationRepositoryImpl(
      JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  @Override
  public Stream<T> findAllInStream(Specification<T> specification) {
    return findAllInStream(specification, null);
  }

  @Override
  public Stream<T> findAllInStream(Specification<T> specification, Sort sort) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDomainClass());
    Root<T> root = criteriaQuery.from(getDomainClass());
    criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));
    if (sort != null && sort.isSorted()) {
      criteriaQuery.orderBy(QueryUtils.toOrders(sort, root, criteriaBuilder));
    }
    return entityManager.createQuery(criteriaQuery).getResultStream();
  }
}
