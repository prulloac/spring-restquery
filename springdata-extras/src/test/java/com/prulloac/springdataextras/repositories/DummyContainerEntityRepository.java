package com.prulloac.springdataextras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.prulloac.springdataextras.schema.DummyContainerEntity;

public interface DummyContainerEntityRepository
    extends JpaRepository<DummyContainerEntity, Long>,
        JpaSpecificationExecutor<DummyContainerEntity> {}
