package com.prulloac.springdata.restquery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.prulloac.springdata.restquery.repositories.RestQuerySpecificationRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(
    basePackages = {"com.prulloac.springdata.restquery.repositories"},
    repositoryBaseClass = RestQuerySpecificationRepositoryImpl.class)
public class TestApplication {}
