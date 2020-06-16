package com.prulloac.springdataextras;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.prulloac.springdataextras.repositories.RestQuerySpecificationRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(
		basePackages = {"com.prulloac.springdataextras.repositories"},
		repositoryBaseClass = RestQuerySpecificationRepositoryImpl.class)
public class TestApplication {}
