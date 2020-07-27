package com.example.gis.model;


import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Test implements Serializable {
  @Id
  private Integer id;
  private String name;
  @Column( columnDefinition = "POINT")
  private Point point;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Test test = (Test) o;
    return id.equals(test.id) &&
            Objects.equals(name, test.name) &&
            Objects.equals(point, test.point);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, point);
  }

  @Override
  public String toString() {
    return "Test{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", point=" + point +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Point getPoint() {
    return point;
  }

  public void setPoint(Point point) {
    this.point = point;
  }

  public Test() {
  }
}
