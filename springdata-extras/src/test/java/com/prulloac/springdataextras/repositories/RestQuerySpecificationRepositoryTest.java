package com.prulloac.springdataextras.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import com.prulloac.springdataextras.restquery.BaseRestQueryConfig;
import com.prulloac.springdataextras.restquery.specification.RestQuery;
import com.prulloac.springdataextras.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class RestQuerySpecificationRepositoryTest extends BaseRestQueryConfig {

	@Autowired
	DummyEntityRepository2 dummyEntityRepository2;

	@Test
	void testFindAllStream() {
		String query = "id>0";
		RestQuery<DummyEntity> dummyEntityRestQuery = RestQuery.buildQuery(DummyEntity.class, query);
		Stream<DummyEntity> allInStream = dummyEntityRepository2.findAllInStream(dummyEntityRestQuery);
		Stream<DummyEntity> allInStream2 = dummyEntityRepository2.findAllInStream(dummyEntityRestQuery, Sort.by("field"));
		assertThat(allInStream.count(), greaterThan(0L));
		assertThat(allInStream2.count(), greaterThan(0L));
	}

}
