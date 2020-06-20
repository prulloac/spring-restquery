package com.prulloac.springdata.restquery.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.stream.Stream;

@NoRepositoryBean
public interface RestQuerySpecificationRepository<T, K extends Serializable>
    extends JpaRepository<T, K>, JpaSpecificationExecutor<T> {

  Stream<T> findAllInStream(Specification<T> specification);

  Stream<T> findAllInStream(Specification<T> specification, Sort sort);
}
