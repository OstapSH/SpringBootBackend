package com.example.springbootbackend.service;

import com.example.springbootbackend.domain.Country;

public interface CountryService extends GeneralService<Country, Integer> {
    void insertCountries(String name);

    void insertIntoCountry(String name);
}
