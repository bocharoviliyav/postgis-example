package com.example.gis.service;

import com.example.gis.model.Airports;
import com.example.gis.repo.AirportsRepo;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportsService {

  private final AirportsRepo airportsRepo;

  public AirportsService(AirportsRepo airportsRepo) {
    this.airportsRepo = airportsRepo;
  }

  public List<Airports> getAllAirports(){
    return airportsRepo.findAll();
  }

  public Airports createSaveAirports(String code,Double lat,Double lon){
    Airports airports = new Airports();
    GeometryFactory gf = new GeometryFactory();
    Point point = gf.createPoint(new Coordinate(lat, lon));
    airports.setCode(code);
    airports.setGeog(point);
    return airportsRepo.save(airports);
  }

  public List<Airports> getInSquare(Double x,Double y, Double delta){
    Coordinate c1 = new Coordinate(x - delta, y - delta);
    Coordinate c2 = new Coordinate(x - delta, y + delta);
    Coordinate c3 = new Coordinate(x + delta, y + delta);
    Coordinate c4 = new Coordinate(x + delta, y - delta);

    Coordinate[] coordinates = new Coordinate[]{c1, c2, c3, c4, c1};

    GeometryFactory geometryFactory = new GeometryFactory();
    Polygon square_window = geometryFactory.createPolygon(coordinates);
    square_window.setSRID(4326);
    return airportsRepo.getAirportsByDelta(square_window);
  }
}
