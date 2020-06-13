package com.prulloac.springdataextras.schema;

import com.prulloac.springdataextras.annotation.FilterableColumn;
import com.prulloac.springdataextras.annotation.SorteableColumn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class DummyContainerEntity {
  @SorteableColumn @FilterableColumn @Column @Id public Long id;

  @SorteableColumn @FilterableColumn @Column public String field;

  @OneToMany(mappedBy = "container")
  public Collection<DummyEntity> children;
}
