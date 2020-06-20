package com.prulloac.springdata.restquery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.prulloac.springdata.restquery.schema.DummyContainerEntity;

public interface DummyContainerEntityRepository
    extends JpaRepository<DummyContainerEntity, Long>,
        JpaSpecificationExecutor<DummyContainerEntity> {}
