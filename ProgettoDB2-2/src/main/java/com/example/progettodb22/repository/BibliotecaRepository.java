package com.example.progettodb22.repository;

import com.example.progettodb22.entity.Biblioteca;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BibliotecaRepository extends MongoRepository<Biblioteca, String> {

    List<Biblioteca> findByLocationNear(Point location, Distance distance);


    @Query("{location : {$near : { 'type': 'Points', coordinates: [$0, $1]}}}")
    List<Biblioteca> findBibliotecaByCoordinates(Double Longitude, Double latitude);

}
