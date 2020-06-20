package com.prulloac.springdata.restquery.schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
public class DummyEntity {
  @Column @Id public Long id;
  @Column public String field;
  @Column public int someInteger;
  @Column public float someFloat;
  @Column public double someDouble;
  @Column public double someShort;
  @Column public BigDecimal someBigDecimal;
  @Enumerated public STATE state;
  @JoinColumn @ManyToOne public DummyContainerEntity container;
  @Column public LocalDate birth;
  @Column public ZonedDateTime register;

  public Long getId() {
    return id;
  }

  public String getField() {
    return field;
  }

  public enum STATE {
    A,
    B;
  }
}
