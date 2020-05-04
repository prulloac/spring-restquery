package com.prulloac.springdataextras.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.prulloac.springdataextras.Pojo;
import com.prulloac.springdataextras.annotation.SorteableColumn;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

public class PageRequestBuilderTest {

	class PojoNotEntity {
		@SorteableColumn
		@Column
		String field;
	}

	@Test
	public void pageRequestNoSort() {
		PageRequest expected = PageRequest.of(0, 10);
		PageRequest expectedInvalidPageNumber = PageRequest.of(0, 10);
		PageRequest expectedInvalidPageSize = PageRequest.of(0, Integer.MAX_VALUE);
		assertThat(PageRequestBuilder.buildRequest(0, 10, null, null),
				equalTo(expected));
		assertThat(PageRequestBuilder.buildRequest(null, 10, null, null),
				equalTo(expectedInvalidPageNumber));
		assertThat(PageRequestBuilder.buildRequest(-1, 10, null, null),
				equalTo(expectedInvalidPageNumber));
		assertThat(PageRequestBuilder.buildRequest(0, null, null, null),
				equalTo(expectedInvalidPageSize));
		assertThat(PageRequestBuilder.buildRequest(0, 0, null, null),
				equalTo(expectedInvalidPageSize));
	}

	@Test
	public void pageRequestWithSort() {
		PageRequest expectedAsc = PageRequest.of(0, 10, Sort.by(ASC, "field"));
		PageRequest expectedDesc = PageRequest.of(0, 10, Sort.by(DESC, "field"));
		PageRequest expectedNoSort = PageRequest.of(0, 10);
		String[] sortComboAsc = new String[]{"field:asc"};
		String[] sortComboFieldNotSorteable = new String[]{"fieldNotSorteable:asc"};
		String[] sortComboFieldNotColumn = new String[]{"fieldNotColumn:asc"};
		String[] sortComboDesc = new String[]{"field:desc"};
		String[] badSortCombo = new String[]{"badField:asc"};
		assertThat(PageRequestBuilder.buildRequest(0, 10, sortComboAsc, Pojo.class),
				equalTo(expectedAsc));
		assertThat(PageRequestBuilder.buildRequest(0, 10, sortComboDesc, Pojo.class),
				equalTo(expectedDesc));
		assertThat(PageRequestBuilder.buildRequest(0, 10, badSortCombo, Pojo.class),
				equalTo(expectedNoSort));
		assertThat(PageRequestBuilder.buildRequest(0, 10, sortComboAsc, PojoNotEntity.class),
				equalTo(expectedNoSort));
		assertThat(PageRequestBuilder.buildRequest(0, 10, sortComboFieldNotColumn, Pojo.class),
				equalTo(expectedNoSort));
		assertThat(PageRequestBuilder.buildRequest(0, 10, sortComboFieldNotSorteable, Pojo.class),
				equalTo(expectedNoSort));
	}

}
