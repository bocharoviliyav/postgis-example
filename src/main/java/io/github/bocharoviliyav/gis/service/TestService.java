package io.github.bocharoviliyav.gis.service;

import io.github.bocharoviliyav.gis.model.Test;
import io.github.bocharoviliyav.gis.repo.TestRepo;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Test service.
 */
@Service
public class TestService {

    private final TestRepo testRepo;

    /**
     * Instantiates a new Test service.
     *
     * @param testRepo the test repo
     */
    public TestService(final TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<Test> getAll() {
        return testRepo.findAll();
    }

    /**
     * Gets nearest three.
     *
     * @param ewkt the ewkt
     * @return the nearest three
     */
    public List<Test> getNearestThree(final String ewkt) {
        return testRepo.findNearest(ewkt);
    }

    /**
     * Gets nearest three.
     *
     * @param x the lat
     * @param y the lon
     * @return the nearest three
     */
    public List<Test> getNearestThree(final Double x, final Double y) {
        GeometryFactory geometryFactory = new GeometryFactory();

        Coordinate coordinate = new Coordinate(x, y);
        Point point = geometryFactory.createPoint(coordinate);
        point.setSRID(4326);

        return testRepo.findNearest(point);
    }

    /**
     * Get list of tests in square.
     *
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param delta the delta
     * @return the list
     */
    public List<Test> getInSquare(final Double x,
                                  final Double y,
                                  final Double delta) {
        Coordinate c1 = new Coordinate(x - delta, y - delta);
        Coordinate c2 = new Coordinate(x - delta, y + delta);
        Coordinate c3 = new Coordinate(x + delta, y + delta);
        Coordinate c4 = new Coordinate(x + delta, y - delta);

        Coordinate[] coordinates = new Coordinate[]{c1, c2, c3, c4, c1};

        GeometryFactory geometryFactory = new GeometryFactory();
        Polygon square = geometryFactory.createPolygon(coordinates);
        square.setSRID(4326);
        return testRepo.getTestsByDelta(square.toString());
    }

    /**
     * Create or update test.
     *
     * @param name the name
     * @param lat  the lat
     * @param lon  the lon
     */
    public void createSaveTest(final String name,
                               final Double lat,
                               final Double lon) {
        testRepo.createOrUpdate(name, lat, lon);
    }
}
