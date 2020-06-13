package com.prulloac.springdataversionableframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.prulloac.springdataversionableframework.schema.BaseVersionEntity;

import java.io.Serializable;

/** @author Prulloac */
@NoRepositoryBean
public interface BaseVersionRepository<T extends BaseVersionEntity<?>, K extends Serializable>
    extends JpaRepository<T, K>, JpaSpecificationExecutor<T> {}
