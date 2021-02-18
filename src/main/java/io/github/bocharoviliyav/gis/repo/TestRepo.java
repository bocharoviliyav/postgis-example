package io.github.bocharoviliyav.gis.repo;

import io.github.bocharoviliyav.gis.model.Test;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The Spring Data interface for Test.
 */
public interface TestRepo extends JpaRepository<Test, Integer> {

    /**
     * Find nearest list.
     *
     * @param ewkt the ewkt
     * @return the list
     */
    @Query(value = "SELECT * FROM public.test ORDER BY ST_Distance(geog, ST_GeomFromEWKT( :ewkt )) LIMIT 3", nativeQuery = true)
    List<Test> findNearest(final String ewkt);

    /**
     * Find nearest list.
     *
     * @param geom the geom
     * @return the list
     */
    @Query(value = "SELECT * FROM public.test ORDER BY ST_Distance(geog,  :geom ) LIMIT 3", nativeQuery = true)
    List<Test> findNearest(final Point geom);

    /**
     * Gets tests by delta.
     *
     * @param window the window
     * @return the tests by delta
     */
    @Query(value = "select * from public.test a where ST_DWithin(a.geog, ST_GeographyFromText(:window),1.0,true) = true", nativeQuery = true)
    List<Test> getTestsByDelta(final String window);

    /**
     * Create or update test.
     *
     * @param name the name
     * @param lat  the lat
     * @param lon  the lon
     */
    @Transactional
    @Modifying
    @Query(value = "insert into test(test_name, geog) values (:name, ST_SetSRID(ST_Point( :lat, :lon ), 4326)\\:\\:geography)", nativeQuery = true)
    void createOrUpdate(final String name, final Double lat, final Double lon);
}
