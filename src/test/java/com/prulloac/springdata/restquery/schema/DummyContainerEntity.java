package com.prulloac.springdata.restquery.schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class DummyContainerEntity {
  @Column @Id public Long id;
  @Column public String field;

  @OneToMany(mappedBy = "container")
  public Collection<DummyEntity> children;
}
