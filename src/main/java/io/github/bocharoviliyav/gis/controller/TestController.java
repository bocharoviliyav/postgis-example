package io.github.bocharoviliyav.gis.controller;

import io.github.bocharoviliyav.gis.model.Test;
import io.github.bocharoviliyav.gis.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Test controller.
 */
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    private final TestService testService;

    /**
     * Instantiates a new Test controller.
     *
     * @param testService the test service
     */
    public TestController(final TestService testService) {
        this.testService = testService;
    }

    /**
     * Get all test records.
     *
     * @return the list of test records
     */
    @GetMapping("/all")
    public ResponseEntity<List<Test>> getAllTestRecords() {
        List<Test> testList = testService.getAll();
        return ResponseEntity.ok(testList);
    }

    /**
     * Gets three nearest test records.
     *
     * @param ewkt SRID=4326;POINT(37.617635 55.755814)
     * @return the three nearest test records
     */
    @GetMapping("/three/ewkt")
    public ResponseEntity<List<Test>> getThreeNearestTestRecords(@RequestParam(required = false) final String ewkt) {
        List<Test> testList = testService.getNearestThree(ewkt);
        return ResponseEntity.ok(testList);
    }

    /**
     * Gets three nearest test records.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the three nearest test records
     */
    @GetMapping("/three")
    public ResponseEntity<List<Test>> getThreeNearestTestRecords(@RequestParam final Double x,
                                                                 @RequestParam final Double y) {

        List<Test> testList = testService.getNearestThree(x, y);
        return ResponseEntity.ok(testList);
    }

    /**
     * Gets all tests in square.
     *
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param delta the delta
     * @return the all test in square
     */
    @GetMapping("/delta")
    public ResponseEntity<List<Test>> getAllTestsInSquare(@RequestParam final Double x,
                                                          @RequestParam final Double y,
                                                          @RequestParam final Double delta) {
        List<Test> testList = testService.getInSquare(x, y, delta);
        return ResponseEntity.ok(testList);
    }

    /**
     * Add or update test.
     *
     * @param lat  the lat
     * @param lon  the lon
     * @param name the name
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity<Void> saveTest(@RequestParam final Double lat,
                                         @RequestParam final Double lon,
                                         @RequestParam final String name) {
        testService.createSaveTest(name, lat, lon);
        return ResponseEntity.ok().build();
    }
}
