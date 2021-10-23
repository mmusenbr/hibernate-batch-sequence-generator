package com.github.marschall.hibernate.batchsequencegenerator.entities;

import static com.github.marschall.hibernate.batchsequencegenerator.BatchSequenceGenerator.FETCH_SIZE_PARAM;
import static com.github.marschall.hibernate.batchsequencegenerator.BatchSequenceGenerator.SEQUENCE_PARAM;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity(name = "PARENT_ENTITY")
public class ParentEntity {

  @Id
  @GenericGenerator(
          name = "parent_id_generator",
          strategy = "com.github.marschall.hibernate.batchsequencegenerator.BatchSequenceGenerator",
          parameters = {
              @Parameter(name = SEQUENCE_PARAM, value = "SEQ_PARENT_ID"),
              @Parameter(name = FETCH_SIZE_PARAM, value = "50")
          })
  @GeneratedValue(generator = "parent_id_generator")
  @Column(name = "PARENT_ID")
  private Long parentId;

  @OneToMany
  @JoinColumn(name = "PARENT_ID")
  private Set<ChildEntity> children = new HashSet<>();

  public Long getParentId() {
    return this.parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Set<ChildEntity> getChildren() {
    return this.children;
  }

  public void setChildren(Set<ChildEntity> children) {
    this.children = children;
  }

  public void addChild(ChildEntity child) {
    this.children.add(child);
  }

}
