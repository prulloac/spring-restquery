package com.prulloac.springdataextras.utils;

import com.prulloac.springdataextras.annotation.FilterableColumn;
import com.prulloac.springdataextras.annotation.SorteableColumn;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prulloac
 */
public class SpecialColumnIdentifier {

	private SpecialColumnIdentifier() throws IllegalAccessException {
		throw new IllegalAccessException("Utility class should not be instantiated");
	}

	public static List<String> getSorteableColumns(Class<?> entity) {
		if (entity.isAnnotationPresent(Entity.class)) {
			return EntityScanUtil.getAllFields(entity)
					.stream()
					.filter(x ->
							x.isAnnotationPresent(SorteableColumn.class) &&
							x.isAnnotationPresent(Column.class))
					.map(Field::getName)
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public static List<String> getFilterableColumns(Class<?> entity) {
		if (entity.isAnnotationPresent(Entity.class)) {
			return EntityScanUtil.getAllFields(entity)
					.stream()
					.filter(x ->
							x.isAnnotationPresent(FilterableColumn.class) &&
									x.isAnnotationPresent(Column.class))
					.map(Field::getName)
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

}
