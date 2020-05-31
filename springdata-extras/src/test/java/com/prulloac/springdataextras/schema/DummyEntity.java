package com.prulloac.springdataextras.schema;

import com.prulloac.springdataextras.annotation.FilterableColumn;
import com.prulloac.springdataextras.annotation.SorteableColumn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DummyEntity {

  @SorteableColumn @FilterableColumn @Column @Id public Long id;

  @SorteableColumn @FilterableColumn @Column public String field;

  @FilterableColumn @Column public int otherField;

  @Column public String fieldNotSorteable;

  @Column public String fieldNotFilterable;

  @SorteableColumn public String fieldNotColumn;
}
