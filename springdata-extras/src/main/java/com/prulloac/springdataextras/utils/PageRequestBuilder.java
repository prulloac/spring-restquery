package com.prulloac.springdataextras.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * @author Prulloac
 */
public interface PageRequestBuilder {

	private static boolean isValidSortCombosSyntax(String[] sortCombos) {
		return Arrays.stream(sortCombos).allMatch(combo ->
				combo.matches("^\\w*:((?i)asc|desc)$"));
	}

	private static Sort.Order propertyToOrder(String property) {
		String[] split = property.split(":");
		final String asc = "ASC";
		if (asc.equalsIgnoreCase(split[1])) {
			return Sort.Order.asc(split[0]);
		} else {
			return Sort.Order.desc(split[0]);
		}
	}

	private static List<String> filterSortFields(String[] sortCombos, List<String> sorteableBy) {
		Assert.isTrue(isValidSortCombosSyntax(sortCombos),
				"combos syntax error: it should be noted as <field>:asc|desc");
		return Arrays.stream(sortCombos)
				.filter(combo -> sorteableBy.contains(combo.split(":")[0]))
				.collect(Collectors.toList());
	}

	static Sort buildSort(String[] sortCombos, Class<?> entity) {
		if (!isEmpty(sortCombos)) {
			List<Sort.Order> sort = filterSortFields(sortCombos, SpecialColumnIdentifier.getSorteableColumns(entity))
					.stream()
					.map(PageRequestBuilder::propertyToOrder)
					.collect(Collectors.toList());
			if (!sort.isEmpty()) {
				return Sort.by(sort);
			}
		}
		return Sort.unsorted();
	}

	static PageRequest buildRequest(Integer pageNumber, Integer pageSize, String[] sortCombos, Class<?> entity) {
		int size = null == pageSize || pageSize < 1 ? Integer.MAX_VALUE : pageSize;
		int page = null == pageNumber || pageNumber < 0 ? 0 : pageNumber;
		Sort sort = buildSort(sortCombos, entity);
		if (sort != Sort.unsorted()) {
			return PageRequest.of(page, size, sort);
		}
		return PageRequest.of(page, size);
	}

}
