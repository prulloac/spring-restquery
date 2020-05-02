package com.prulloac.springdataextras.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Prulloac
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface SorteableColumn {

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.FIELD, ElementType.METHOD})
	@interface Exclude {
	}

}
