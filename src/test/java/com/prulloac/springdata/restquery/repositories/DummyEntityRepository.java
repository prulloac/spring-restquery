package com.prulloac.springdata.restquery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.prulloac.springdata.restquery.schema.DummyEntity;

public interface DummyEntityRepository
    extends JpaRepository<DummyEntity, Long>, JpaSpecificationExecutor<DummyEntity> {}
