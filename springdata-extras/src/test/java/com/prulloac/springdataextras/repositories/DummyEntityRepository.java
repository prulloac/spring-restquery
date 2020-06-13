package com.prulloac.springdataextras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.prulloac.springdataextras.schema.DummyEntity;

public interface DummyEntityRepository
    extends JpaRepository<DummyEntity, Long>, JpaSpecificationExecutor<DummyEntity> {}
