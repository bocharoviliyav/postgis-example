package com.example.gis.repo;

import com.example.gis.model.Test;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TestRepo extends JpaRepository<Test, Integer> {
  @Query(value = "SELECT * FROM public.test ORDER BY ST_Distance(point, ST_GeomFromEWKT( :ewkt )) LIMIT 3",nativeQuery = true)
  List<Test> findNearest(String ewkt);

  @Query(value = "SELECT * FROM public.test ORDER BY ST_Distance(point,  :geom ) LIMIT 3",nativeQuery = true)
  List<Test> findNearest(Point geom);

}
