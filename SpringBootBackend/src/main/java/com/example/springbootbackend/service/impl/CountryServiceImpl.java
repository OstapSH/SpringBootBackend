package com.example.springbootbackend.service.impl;

import com.example.springbootbackend.domain.Country;
import com.example.springbootbackend.exception.CountryNotFoundException;
import com.example.springbootbackend.exception.UsersExistForCountryException;
import com.example.springbootbackend.exception.PlaceNotFoundException;
import com.example.springbootbackend.repository.CountryRepository;
import com.example.springbootbackend.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }

    @Transactional
    public Country create(Country country) {
        countryRepository.save(country);
        return country;
    }

    @Transactional
    public void update(Integer id, Country uCountry) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        country.setName(uCountry.getName());
        country.setUsers(uCountry.getUsers());
        countryRepository.save(country);
    }

    @Transactional
    public void delete(Integer id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        if (!country.getUsers().isEmpty()) throw new UsersExistForCountryException(id);
        countryRepository.delete(country);
    }

    @Override
    @Transactional
    public void insertCountries(String countryName) {
        countryRepository.insertCountries(countryName);
    }

    @Override
    @Transactional
    public void insertIntoCountry(String name) {
        countryRepository.insertIntoCountry(name);
    }
}
