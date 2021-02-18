package io.github.bocharoviliyav.gis.model;


import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * The Test model.
 */
@Entity
public class Test implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "test_name")
    private String name;

    @Column(columnDefinition = "geography")
    private Geometry geog;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) && Objects.equals(name, test.name)  && Objects.equals(geog, test.geog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, geog);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", geog=" + geog +
                '}';
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }


    /**
     * Gets geog.
     *
     * @return the geog
     */
    public Geometry getGeog() {
        return geog;
    }

    /**
     * Sets geog.
     *
     * @param geog the geog
     */
    public void setGeog(Geometry geog) {
        this.geog = geog;
    }

    /**
     * Instantiates a new Test.
     */
    public Test() {
    }
}
