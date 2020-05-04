package com.prulloac.springdataextras.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import com.google.re2j.Pattern;
import com.prulloac.springdataextras.utils.SpecialColumnIdentifier;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * @author Prulloac
 */
public interface SpecificationBuilder {

	private static SearchCondition createSearchCondition(String filter) {
		String[] split = filter.split(":");
		SearchCondition criteria = new SearchCondition()
				.setField(split[0])
				.setOperation(QueryOperation.parse(split[1]));
		int binaryOperationLength = 3;
		if (split.length == binaryOperationLength) {
			criteria.setValue(split[2]);
		}
		return criteria;
	}


	private static boolean isValidFilterSyntax(String[] filters) {
		String regex = "^\\w*:("+QueryOperation.UNARY_OPERATORS_REGEX+"|"+QueryOperation.BINARY_OPERATORS_REGEX+":\\w*)$";
		return Arrays.stream(filters).allMatch(filter ->
				Pattern.compile(regex).matcher(filter).matches());
	}

	private static List<String> filterFilters(String[] filters, List<String> fields) {
		Assert.isTrue(isValidFilterSyntax(filters),
				"filter syntax error: it should be noted as <field>:<operation>[:<value>]");
		return Arrays.stream(filters)
				.filter(filter -> fields.contains(filter.split(":")[0]))
				.collect(Collectors.toList());
	}

	static <T> Specification<T> build(String[] filters, Class<T> entityClass) {
		if (isEmpty(filters)) {
			return null;
		}
		List<String> fields = SpecialColumnIdentifier.getFilterableColumns(entityClass);
		List<DynamicSpecification<T>> cleansedFilters = filterFilters(filters, fields)
				.stream()
				.map(SpecificationBuilder::createSearchCondition)
				.map(DynamicSpecification<T>::new)
				.collect(Collectors.toList())
				;
		if (cleansedFilters.isEmpty()) {
			return null;
		}
		Specification<T> result = cleansedFilters.get(0);
		for (int i = 1; i < cleansedFilters.size(); i++) {
			result = Objects.requireNonNull(Specification.where(result))
					.and(cleansedFilters.get(i));
		}
		return result;
	}

}
