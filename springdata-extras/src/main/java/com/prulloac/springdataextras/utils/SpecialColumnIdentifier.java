package com.prulloac.springdataextras.utils;

import com.prulloac.springdataextras.annotation.FilterableColumn;
import com.prulloac.springdataextras.annotation.SorteableColumn;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** @author Prulloac */
public interface SpecialColumnIdentifier {

  private static Predicate<Field> isColumn() {
    return field -> field.isAnnotationPresent(Column.class);
  }

  private static <T extends Annotation> Predicate<Field> isSpecialColumnType(Class<T> annotation) {
    return isColumn().and(field -> field.isAnnotationPresent(annotation));
  }

  static List<String> getSorteableColumns(Class<?> entity) {
    if (entity.isAnnotationPresent(Entity.class)) {
      return EntityScanUtil.getAllFields(entity).stream()
          .filter(isSpecialColumnType(SorteableColumn.class))
          .map(Field::getName)
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  static List<String> getFilterableColumns(Class<?> entity) {
    if (entity.isAnnotationPresent(Entity.class)) {
      return EntityScanUtil.getAllFields(entity).stream()
          .filter(isSpecialColumnType(FilterableColumn.class))
          .map(Field::getName)
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }
}
