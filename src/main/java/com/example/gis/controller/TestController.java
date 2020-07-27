package com.example.gis.controller;

import com.example.gis.model.Test;
import com.example.gis.service.TestService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.SaslServer;
import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

  private final TestService testService;

  public TestController(TestService testService) {
    this.testService = testService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Test>> getAllTestRecords() {
    List<Test> testList = testService.getAll();
    testList.forEach(System.out::println);
    return ResponseEntity.ok(testList);
  }

  @GetMapping("/three/ewkt")
  public ResponseEntity<List<Test>> getThreeNearestTestRecords(@RequestParam(required = false) String ewkt) {
    if (ewkt ==null || ewkt.isEmpty())
      ewkt = "SRID=4326;POINT(37.617635 55.755814)";
    List<Test> testList = testService.getNearestThree(ewkt);
    testList.forEach(System.out::println);
    return ResponseEntity.ok(testList);
  }

  @GetMapping("/three")
  public ResponseEntity<List<Test>> getThreeNearestTestRecords(@RequestParam Double x, @RequestParam Double y) {
    GeometryFactory geometryFactory = new GeometryFactory();

    Coordinate coordinate = new Coordinate(x, y);
    Point point = geometryFactory.createPoint(coordinate);
    point.setSRID(4326);
    List<Test> testList = testService.getNearestThree(point);
    testList.forEach(System.out::println);
    return ResponseEntity.ok(testList);
  }
}
