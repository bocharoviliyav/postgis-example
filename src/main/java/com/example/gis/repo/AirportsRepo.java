package com.example.gis.repo;

import com.example.gis.model.Airports;
import org.locationtech.jts.geom.Polygon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AirportsRepo extends JpaRepository<Airports,String> {

  @Query(value = "select * from public.airports a where ST_Within(a.geog, Geography(ST_Transform(:window ,4326))) = true",nativeQuery = true)
  List<Airports> getAirportsByDelta(Polygon window);
}
