package com.prulloac.springdataextras.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Prulloac
 */
public class EntityScanUtil {

	private EntityScanUtil() throws IllegalAccessException {
		throw new IllegalAccessException("Utility class should not be instantiated");
	}

	public static List<Field> getAllFields(Class<?> type) {
		return getAllFields(new ArrayList<>(), type);
	}

	private static List<Field> getAllFields(List<Field> fieldList, Class<?> type) {
		fieldList.addAll(Arrays.asList(type.getDeclaredFields()));
		if (type.getSuperclass() != null) {
			getAllFields(fieldList, type.getSuperclass());
		}
		return fieldList;
	}

}
