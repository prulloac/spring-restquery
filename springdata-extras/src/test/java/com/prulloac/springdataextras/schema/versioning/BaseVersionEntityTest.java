package com.prulloac.springdataextras.schema.versioning;

import org.springframework.beans.InvalidPropertyException;

import com.prulloac.springdataextras.errors.InvalidVersionComparisonException;
import com.prulloac.springdataextras.schema.DummyVersionEntity;
import com.prulloac.springdataextras.schema.versioning.strategy.Draft;
import com.prulloac.springdataextras.schema.versioning.strategy.Identity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaseVersionEntityTest {
  DummyVersionEntity dummyVersionEntity;

  @BeforeEach
  void setUp() {
    dummyVersionEntity = new DummyVersionEntity();
  }

  @Test
  void getContent() {
    assertEquals("test", dummyVersionEntity.getContent());
  }

  @Test
  @Order(1)
  void setAndGetId() {
    assertDoesNotThrow(() -> {
      dummyVersionEntity.setId(1L);
    });
    assertEquals(1L, dummyVersionEntity.getId());
  }

  @Test
  @Order(2)
  void setAndGetVersionStrategyImplementation() {
    assertDoesNotThrow(() -> {
      dummyVersionEntity.setVersionStrategyImplementation(Identity.class);
    });
    assertEquals(Identity.class.getCanonicalName(), dummyVersionEntity.getVersionStrategyImplementation());
  }

  @Test
  @Order(3)
  void setAndGetVersion() {
    String version = "1";
    assertThrows(InvalidPropertyException.class, () -> {
      dummyVersionEntity.setVersion(version);
    });
    assertDoesNotThrow(() -> {
      dummyVersionEntity.setVersionStrategyImplementation(Identity.class);
    });
    assertThrows(InvalidPropertyException.class, () -> {
      dummyVersionEntity.setVersion("1.0.0");
    });
    assertDoesNotThrow(() -> {
      dummyVersionEntity.setVersion(version);
    });
    assertEquals(version, dummyVersionEntity.getVersion());
  }

  @Test
  @Order(4)
  void setAndGetRecordTimestamp() {
    LocalDateTime now = LocalDateTime.now();
    assertDoesNotThrow(() -> {
      dummyVersionEntity.setRecordTimestamp(now);
    });
    assertEquals(now, dummyVersionEntity.getRecordTimestamp());
  }

  @Test
  @Order(5)
  void getVersionStrategyName() {
    assertDoesNotThrow(() -> {
      dummyVersionEntity.setVersionStrategyImplementation(Identity.class);
    });
    assertEquals("Identity", dummyVersionEntity.getVersionStrategyName());
  }

  @Test
  void compareTo() {
    LocalDateTime time = LocalDate.now().atStartOfDay();
    dummyVersionEntity.setId(1L);
    dummyVersionEntity.setVersionStrategyImplementation(Identity.class);
    dummyVersionEntity.setVersion("1");
    dummyVersionEntity.setRecordTimestamp(time);
    assertThrows(InvalidVersionComparisonException.class, () -> {
      dummyVersionEntity.compareTo(null);
    });
    DummyVersionEntity other = new DummyVersionEntity();
    other.setId(1L);
    other.setVersionStrategyImplementation(Draft.class);
    other.setRecordTimestamp(time);
    assertThrows(InvalidVersionComparisonException.class, () -> {
      dummyVersionEntity.compareTo(other);
    });
    other.setVersionStrategyImplementation(Identity.class);
    other.setVersion("1");
    assertEquals(0, dummyVersionEntity.compareTo(other));
  }

  @Test
  void testEquals() {
    LocalDateTime time = LocalDate.now().atStartOfDay();
    dummyVersionEntity.setId(1L);
    dummyVersionEntity.setVersionStrategyImplementation(Identity.class);
    dummyVersionEntity.setVersion("1");
    dummyVersionEntity.setRecordTimestamp(time);
    assertTrue(dummyVersionEntity.equals(dummyVersionEntity));
    assertFalse(dummyVersionEntity.equals(null));
    assertFalse(dummyVersionEntity.equals(1));
    DummyVersionEntity other = new DummyVersionEntity();
    other.content = "TEST";
    assertFalse(dummyVersionEntity.equals(other));
    other.setId(1L);
    assertFalse(dummyVersionEntity.equals(other));
    other.setVersionStrategyImplementation(Identity.class);
    assertFalse(dummyVersionEntity.equals(other));
    other.setVersion("1");
    assertFalse(dummyVersionEntity.equals(other));
    other.content = dummyVersionEntity.getContent();
    assertFalse(dummyVersionEntity.equals(other));
    other.setRecordTimestamp(time);
    assertTrue(dummyVersionEntity.equals(other));
  }

  @Test
  void testHashCode() {
    LocalDateTime time = LocalDate.now().atStartOfDay();
    DummyVersionEntity base = new DummyVersionEntity();
    base.setRecordTimestamp(time);
    dummyVersionEntity.setRecordTimestamp(time);
    assertEquals(base.hashCode(), dummyVersionEntity.hashCode());
    dummyVersionEntity.setId(1L);
    assertNotEquals(base.hashCode(), dummyVersionEntity.hashCode());
  }
}