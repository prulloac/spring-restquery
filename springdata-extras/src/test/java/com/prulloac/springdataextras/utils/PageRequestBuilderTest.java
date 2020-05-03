package com.prulloac.springdataextras.utils;

import org.springframework.data.domain.PageRequest;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PageRequestBuilderTest {

	@Test
	public void pageRequestNoSort() {
		PageRequest expected = PageRequest.of(0, 10);
		assertThat(PageRequestBuilder.buildRequest(0, 10, null, null),
				equalTo(expected));
	}

}
