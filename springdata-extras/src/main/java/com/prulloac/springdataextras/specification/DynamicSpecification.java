package com.prulloac.springdataextras.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

/**
 * @author Prulloac
 */
public class DynamicSpecification<T> implements Specification<T> {

	private final transient SearchCondition condition;

	public DynamicSpecification(SearchCondition condition) {
		this.condition = condition;
	}

	public static <T> DynamicSpecification<T> of(SearchCondition condition) {
		return new DynamicSpecification<>(condition);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		switch (condition.getOperation()) {
			case EQUALS:
				return criteriaBuilder.equal(root.get(condition.field), condition.value);
			case EQUALS_IGNORE_CASE:
				return criteriaBuilder.equal(criteriaBuilder.upper(root.get(condition.field)),
						((String) condition.value).toUpperCase());
			case STARTS_WITH:
				return criteriaBuilder.like(root.get(condition.field), condition.value+"%");
			case STARTS_WITH_IGNORE_CASE:
				return criteriaBuilder.like(criteriaBuilder.upper(root.get(condition.field)),
						((String) condition.value).toUpperCase()+"%");
			case ENDS_WITH:
				return criteriaBuilder.like(root.get(condition.field), "%"+ condition.value);
			case ENDS_WITH_IGNORE_CASE:
				return criteriaBuilder.like(criteriaBuilder.upper(root.get(condition.field)),
						"%"+((String) condition.value).toUpperCase());
			case CONTAINS:
			case LIKE:
				return criteriaBuilder.like(root.get(condition.field), "%"+ condition.value+"%");
			case CONTAINS_IGNORE_CASE:
			case LIKE_IGNORE_CASE:
				return criteriaBuilder.like(criteriaBuilder.upper(root.get(condition.field)),
						"%"+((String) condition.value).toUpperCase()+"%");
			case LIKE_OR_NULL:
				return criteriaBuilder.or(criteriaBuilder.like(root.get(condition.field), "%"+ condition.value+"%"),
						criteriaBuilder.isNull(root.get(condition.field)));
			case LIKE_OR_NULL_IGNORE_CASE:
				return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.upper(root.get(condition.field)),
						"%"+((String) condition.value).toUpperCase()+"%"),
						criteriaBuilder.isNull(root.get(condition.field)));
			case GREATER_THAN:
				return criteriaBuilder.gt(root.get(condition.field), (Number) condition.value);
			case GREATER_THAN_EQUALS:
				return criteriaBuilder.ge(root.get(condition.field), (Number) condition.value);
			case LESS_THAN:
				return criteriaBuilder.lt(root.get(condition.field), (Number) condition.value);
			case LESS_THAN_EQUALS:
				return criteriaBuilder.le(root.get(condition.field), (Number) condition.value);
			case IN:
				CriteriaBuilder.In<String> in = criteriaBuilder
						.in(root.get(condition.field)
						.as(String.class));
				Arrays.stream(condition.value.toString().split(","))
						.forEach(in::value);
				return in;
			case NOT_IN:
				CriteriaBuilder.In<Object> notIn = criteriaBuilder
						.in(root.get(condition.field)
						.as(String.class));
				Arrays.stream(condition.value.toString().split(","))
						.forEach(notIn::value);
				return criteriaBuilder.not(notIn);
			case IS_NULL:
				return criteriaBuilder.isNull(root.get(condition.field));
			default:
				return criteriaBuilder.isNotNull(root.get(condition.field));
		}
	}

}
