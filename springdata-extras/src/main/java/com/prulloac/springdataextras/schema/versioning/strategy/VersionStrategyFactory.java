package com.prulloac.springdataextras.schema.versioning.strategy;

import com.prulloac.springdataextras.errors.VersionStrategyNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/** @author Prulloac */
public class VersionStrategyFactory {
  private static final ConcurrentMap<String, VersionStrategy> INSTANCES = new ConcurrentHashMap<>();

  private VersionStrategyFactory() {
    throw new IllegalStateException("Utility class");
  }

  public static VersionStrategy getInstance(String versionStrategyImplementation) {
    if (INSTANCES.containsKey(versionStrategyImplementation)) {
      return INSTANCES.get(versionStrategyImplementation);
    }
    Class<?> candidate = null;
    try {
      candidate = Class.forName(versionStrategyImplementation);
    } catch (ClassNotFoundException e) {
      throw new VersionStrategyNotFoundException(e);
    }
    Class<?>[] interfaces = candidate.getInterfaces();
    for (Class<?> c : interfaces) {
      if (c.equals(VersionStrategy.class)) {
        VersionStrategy strategy = null;
        try {
          strategy = (VersionStrategy) candidate.getConstructor().newInstance();
        } catch (InstantiationException
            | IllegalAccessException
            | NoSuchMethodException
            | InvocationTargetException e) {
          throw new VersionStrategyNotFoundException(e);
        }
        INSTANCES.put(versionStrategyImplementation, strategy);
        return INSTANCES.get(versionStrategyImplementation);
      }
    }
    throw new VersionStrategyNotFoundException(
        versionStrategyImplementation + " is not a valid VersionStrategy implementation");
  }
}
