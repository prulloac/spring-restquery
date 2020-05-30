package com.prulloac.springdataextras.schema.versioning;

import org.springframework.beans.InvalidPropertyException;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.prulloac.springdataextras.errors.InvalidVersionComparisonException;
import com.prulloac.springdataextras.schema.versioning.strategy.VersionStrategyFactory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

/** @author Prulloac */
@MappedSuperclass
public abstract class BaseVersionEntity<T extends VersionContent>
    implements Comparable<BaseVersionEntity<T>> {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String version;

  @Column(nullable = false)
  private String versionStrategyImplementation;

  @Column(updatable = false, nullable = false)
  private LocalDateTime recordTimestamp = LocalDateTime.now();

  public abstract T getContent();

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getVersion() {
    return this.version;
  }

  public void setVersion(String version) {
    if (isNull(versionStrategyImplementation)) {
      throw new InvalidPropertyException(
          getClass(), "version", "cannot set a version without a versionStrategy");
    }
    if (VersionStrategyFactory.getInstance(versionStrategyImplementation).isVersionValid(version)) {
      this.version = version;
      return;
    }
    throw new InvalidPropertyException(
        getClass(), "version", "version must be valid for the selected strategy");
  }

  public String getVersionStrategyImplementation() {
    return this.versionStrategyImplementation;
  }

  public void setVersionStrategyImplementation(String versionStrategyImplementation) {
    this.versionStrategyImplementation = versionStrategyImplementation;
  }

  public void setVersionStrategyImplementation(Class implementation) {
    setVersionStrategyImplementation(implementation.getCanonicalName());
  }

  public LocalDateTime getRecordTimestamp() {
    return this.recordTimestamp;
  }

  public void setRecordTimestamp(LocalDateTime recordTimestamp) {
    this.recordTimestamp = recordTimestamp;
  }

  @Transient
  public String getVersionStrategyName() {
    return VersionStrategyFactory.getInstance(getVersionStrategyImplementation()).name();
  }

  @Override
  public int compareTo(BaseVersionEntity<T> that) {
    if (isNull(that)
        || !Objects.equal(versionStrategyImplementation, that.versionStrategyImplementation)) {
      throw new InvalidVersionComparisonException();
    }
    return ComparisonChain.start()
        .compare(
            this,
            that,
            VersionStrategyFactory.getInstance(versionStrategyImplementation).comparator())
        .compare(this.recordTimestamp, that.recordTimestamp)
        .compare(this.id, that.id)
        .result();
  }

  @Override
  public boolean equals(Object thatObject) {
    if (this == thatObject) {
      return true;
    }
    if (thatObject == null || getClass() != thatObject.getClass()) {
      return false;
    }
    BaseVersionEntity<?> that = (BaseVersionEntity<?>) thatObject;
    return Objects.equal(id, that.id)
        && Objects.equal(version, that.version)
        && versionStrategyImplementation == that.versionStrategyImplementation
        && Objects.equal(recordTimestamp, that.recordTimestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, version, versionStrategyImplementation, recordTimestamp);
  }
}
