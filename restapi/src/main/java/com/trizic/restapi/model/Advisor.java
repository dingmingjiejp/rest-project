package com.trizic.restapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Advisor {

  @Id
  private String id;

  @NotNull
  private String Name;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public static Advisor of(String id, String name) {
    Advisor advisor = new Advisor();
    advisor.setId(id);
    advisor.setName(name);
    return advisor;
  }
}
