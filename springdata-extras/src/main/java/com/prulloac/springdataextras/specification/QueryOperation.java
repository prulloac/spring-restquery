package com.prulloac.springdataextras.specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

/** @author Prulloac */
public enum QueryOperation {
  /**
   * Represents a JPQL '=' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#equal(Expression, Object)
   */
  EQUALS("eq"),

  /**
   * Represents a JPQL '=' predicate using uppercase in both sides of the comparison
   *
   * @see javax.persistence.criteria.CriteriaBuilder#equal(Expression, Object)
   */
  EQUALS_IGNORE_CASE("eqIgnoreCase"),

  /**
   * Represents a JPQL '<' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#lt(Expression, Number)
   */
  LESS_THAN("lt"),

  /**
   * Represents a JPQL '<=' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#le(Expression, Number)
   */
  LESS_THAN_EQUALS("lte"),

  /**
   * Represents a JPQL '>' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#gt(Expression, Number)
   */
  GREATER_THAN("gt"),

  /**
   * Represents a JPQL '>=' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#ge(Expression, Number)
   */
  GREATER_THAN_EQUALS("gte"),

  /**
   * Represents a JPQL 'in' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#in(javax.persistence.criteria.Expression)
   */
  IN("in"),

  /**
   * Represents a JPQL 'not in' predicate consisting in applying a not predicate that negates the
   * result of the predicate generated with {@link
   * javax.persistence.criteria.CriteriaBuilder#in(Expression)}
   *
   * @see javax.persistence.criteria.CriteriaBuilder#not(Expression)
   */
  NOT_IN("notIn"),

  /**
   * Represents a JPQL 'like' predicate with a wildcard as suffix and prefix
   *
   * @see javax.persistence.criteria.CriteriaBuilder#like(Expression, String)
   */
  LIKE("like"),

  /**
   * Represents a JPQL 'like' predicate with a wildcard as suffix and prefix and uppercase applied
   * to both sides of the comparison
   *
   * @see javax.persistence.criteria.CriteriaBuilder#like(Expression, String)
   */
  LIKE_IGNORE_CASE("likeIgnoreCase"),

  /**
   * Represents a JPQL 'or' predicate that combines with a logic OR both the predicates of a 'like'
   * with a wildcard as suffix and prefix, and a 'is null' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#or(Predicate...)
   */
  LIKE_OR_NULL("likeOrNull"),

  /**
   * Represents a JPQL 'or' predicate that combines with a logic OR both the predicates of a 'like'
   * with a wildcard as suffix and prefix and uppercase applied to both sides of the operation, and
   * a 'is null' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#or(Predicate...)
   */
  LIKE_OR_NULL_IGNORE_CASE("likeOrNullIgnoreCase"),

  /** alias for {@link QueryOperation#LIKE} */
  CONTAINS("contains"),

  /** alias for {@link QueryOperation#LIKE_IGNORE_CASE} */
  CONTAINS_IGNORE_CASE("containsIgnoreCase"),

  /**
   * Represents a JPQL 'like' predicate with a wildcard as suffix
   *
   * @see javax.persistence.criteria.CriteriaBuilder#like(Expression, String)
   */
  STARTS_WITH("startsWith"),

  /**
   * Represents a JPQL 'like' predicate with a wildcard as suffix and uppercase applied to both
   * sides of the comparison
   *
   * @see javax.persistence.criteria.CriteriaBuilder#like(Expression, String)
   */
  STARTS_WITH_IGNORE_CASE("startsWithIgnoreCase"),

  /**
   * Represents a JPQL 'like' predicate with a wildcard as prefix
   *
   * @see javax.persistence.criteria.CriteriaBuilder#like(Expression, String)
   */
  ENDS_WITH("endsWith"),

  /**
   * Represents a JPQL 'like' predicate with a wildcard as prefix and uppercase applied to both
   * sides of the comparison
   *
   * @see javax.persistence.criteria.CriteriaBuilder#like(Expression, String)
   */
  ENDS_WITH_IGNORE_CASE("endsWithIgnoreCase"),

  /**
   * Represents a JPQL 'is null' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#isNull(Expression)
   */
  IS_NULL("isNull"),

  /**
   * Represents a JPQL 'is not null' predicate
   *
   * @see javax.persistence.criteria.CriteriaBuilder#isNotNull(Expression)
   */
  NOT_NULL("notNull"),
  ;

  public static final String BINARY_OPERATORS_REGEX =
      "(eq|eqIgnoreCase|lt|lte|gt|gte|in|notIn|like|"
          + "likeIgnoreCase|likeOrNull|likeOrNullIgnoreCase|contains|containsIgnoreCase|startsWith|"
          + "startsWithIgnoreCase|endsWith|endsWithIgnoreCase)";
  public static final String UNARY_OPERATORS_REGEX = "(isNull|notNull)";

  private final String identifier;

  QueryOperation(String identifier) {
    this.identifier = identifier;
  }

  public String getIdentifier() {
    return identifier;
  }

  public static QueryOperation parse(String operation) {
    for (QueryOperation o : QueryOperation.values()) {
      if (o.identifier.equals(operation)) {
        return o;
      }
    }
    return null;
  }
}
