package com.prulloac.springdataextras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PojoRepository extends JpaRepository<Pojo, Long>, JpaSpecificationExecutor<Pojo> {
}
