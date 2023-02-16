package com.example.springbootbackend.repository;

import com.example.springbootbackend.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Procedure
    void insertCountries(String countryName);

    @Procedure
    void insertIntoCountry(String countryName);
}
