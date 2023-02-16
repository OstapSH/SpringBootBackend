package com.example.springbootbackend.repository;

import com.example.springbootbackend.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    @Procedure
    int getMinPrice();
}
