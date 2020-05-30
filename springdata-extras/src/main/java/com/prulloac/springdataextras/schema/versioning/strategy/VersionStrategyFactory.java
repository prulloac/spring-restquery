package com.prulloac.springdataextras.schema.versioning.strategy;

import com.prulloac.springdataextras.errors.VersionStrategyNotFoundException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/** @author Prulloac */
public class VersionStrategyFactory {
  private static final ConcurrentMap<String, VersionStrategy> INSTANCES = new ConcurrentHashMap<>();

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
          strategy = (VersionStrategy) candidate.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
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
