package com.prulloac.springdataextras.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
		Stream<DummyEntity> allInStream = dummyEntityRepository2.findAllInStream(RestQuery.buildQuery(DummyEntity.class, query));
		assertThat(allInStream.count(), greaterThan(0L));
	}

}
