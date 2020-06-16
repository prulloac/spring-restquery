package com.prulloac.springdataextras.restquery.nodes.comparison;

import java.util.List;

public abstract class NumericComparisonNode extends ComparisonNode {
  protected NumericComparisonNode(String field, List<Object> arguments) {
    super(field, arguments);
  }

  protected NumericComparisonNode(String field, Object value) {
    super(field, value);
  }

  public static boolean isNativeNumericType(Class<?> type) {
    if (type.equals(Integer.class) || type.equals(int.class)) {
      return true;
    }
    if (type.equals(Long.class) || type.equals(long.class)) {
      return true;
    }
    if (type.equals(Double.class) || type.equals(double.class)) {
      return true;
    }
    if (type.equals(Float.class) || type.equals(float.class)) {
      return true;
    }
    return type.equals(Short.class) || type.equals(short.class);
  }
}
