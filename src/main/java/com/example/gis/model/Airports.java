package com.example.gis.model;

import org.locationtech.jts.geom.Point;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Airports implements Serializable {

  public Airports() {
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Airports airports = (Airports) o;
    return code.equals(airports.code) &&
            Objects.equals(geog, airports.geog);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, geog);
  }

  @Override
  public String toString() {
    return "Airports{" +
            "code='" + code + '\'' +
            ", geog=" + geog +
            '}';
  }

  @Id
  private String code;

  public Point getGeog() {
    return geog;
  }

  public void setGeog(Point geog) {
    this.geog = geog;
  }

  private Point geog;
}
