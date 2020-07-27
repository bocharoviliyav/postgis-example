package com.example.gis.service;

import com.example.gis.model.Test;
import com.example.gis.repo.TestRepo;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

  private final TestRepo testRepo;

  public TestService(TestRepo testRepo) {
    this.testRepo = testRepo;
  }

  public List<Test> getAll() {
    return testRepo.findAll();
  }

  public List<Test> getNearestThree(String ewkt) {
    return testRepo.findNearest(ewkt);
  }

  public List<Test> getNearestThree(Point geom) {
    return testRepo.findNearest(geom);
  }
}
