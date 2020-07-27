package com.example.gis.controller;

import com.example.gis.model.Airports;
import com.example.gis.service.AirportsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportsController {

  private final AirportsService airportsService;

  public AirportsController(AirportsService airportsService) {
    this.airportsService = airportsService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Airports>> getAllAirports() {
    List<Airports> airports = airportsService.getAllAirports();

    airports.forEach(System.out::println);

    return ResponseEntity.ok(airports);
  }
  @GetMapping("/delta")
  public ResponseEntity<List<Airports>> getAllAirportsInSquare(@RequestParam Double x, @RequestParam Double y, @RequestParam Double delta) {
    List<Airports> airports = airportsService.getInSquare(x,y,delta);

    airports.forEach(System.out::println);

    return ResponseEntity.ok(airports);
  }

  @PostMapping("")
  public ResponseEntity<Airports> saveAirports(@RequestParam Double lat, @RequestParam Double lon, @RequestParam String code) {
    return ResponseEntity.ok(airportsService.createSaveAirports(code,lat,lon));
  }
}
