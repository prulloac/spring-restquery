package com.prulloac.springdataextras.schema;

import com.prulloac.springdataextras.annotation.FilterableColumn;
import com.prulloac.springdataextras.annotation.SorteableColumn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
public class DummyEntity {

  @SorteableColumn @FilterableColumn @Column @Id public Long id;

  @SorteableColumn @FilterableColumn @Column public String field;

  @Column public int age;

  @Column public float height;

  @FilterableColumn @Column public int otherField;

  @Column public String fieldNotSorteable;

  @Column public String fieldNotFilterable;

  @SorteableColumn public String fieldNotColumn;

  @JoinColumn @ManyToOne public DummyContainerEntity container;

  @Column public LocalDate birth;

  @Column public ZonedDateTime register;

  public Long getId() {
    return id;
  }

  public String getField() {
    return field;
  }

  public int getOtherField() {
    return otherField;
  }

  public String getFieldNotSorteable() {
    return fieldNotSorteable;
  }

  public String getFieldNotFilterable() {
    return fieldNotFilterable;
  }

  public String getFieldNotColumn() {
    return fieldNotColumn;
  }
}
