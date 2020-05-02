package com.prulloac.springdataextras.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Prulloac
 */
public class DynamicSpecification<T> implements Specification<T> {

	private final transient SearchCondition condition;

	public DynamicSpecification(SearchCondition condition) {
		this.condition = condition;
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
				return criteriaBuilder.like(root.get(condition.field), "%"+ condition.value+"%");
			case CONTAINS_IGNORE_CASE:
				return criteriaBuilder.like(criteriaBuilder.upper(root.get(condition.field)),
						"%"+((String) condition.value).toUpperCase()+"%");
			case LIKE:
				return criteriaBuilder.like(root.get(condition.field), "%"+ condition.value+"%");
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
				return criteriaBuilder.in(root.get(condition.field)).value(condition.value);
			case NOT_IN:
				return criteriaBuilder.not(criteriaBuilder.in(root.get(condition.field)).value(condition.value));
			case IS_NULL:
				return criteriaBuilder.isNull(root.get(condition.field));
			case NOT_NULL:
				return criteriaBuilder.isNotNull(root.get(condition.field));
			case BEFORE:
				return buildBefore(root, criteriaBuilder, condition);
			case AFTER:
				return buildAfter(root, criteriaBuilder, condition);
			default:
				return null;
		}
	}

	private Predicate buildAfter(Root<T> root, CriteriaBuilder criteriaBuilder, SearchCondition criteria) {
		try {
			return criteriaBuilder.greaterThan(root.get(criteria.field).as(Timestamp.class),
					Timestamp.valueOf(LocalDateTime.parse((String)criteria.value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
		} catch (Exception e) {
			return criteriaBuilder.greaterThan(root.get(criteria.field).as(Date.class),
					toDate(LocalDate.parse((String)criteria.value, DateTimeFormatter.ISO_LOCAL_DATE)));
		}
	}

	private Predicate buildBefore(Root<T> root, CriteriaBuilder criteriaBuilder, SearchCondition criteria) {
		try {
			return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.field).as(Timestamp.class),
					Timestamp.valueOf(LocalDateTime.parse((String)criteria.value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
		} catch (Exception e) {
			return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.field).as(Date.class),
					toDate(LocalDate.parse((String)criteria.value, DateTimeFormatter.ISO_LOCAL_DATE)));
		}
	}

	private Date toDate(ChronoLocalDate field) {
		return new Date(field.toEpochDay());
	}

}
