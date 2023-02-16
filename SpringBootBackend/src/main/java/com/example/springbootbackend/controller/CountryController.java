package com.example.springbootbackend.controller;

import com.example.springbootbackend.domain.Country;
import com.example.springbootbackend.dto.CountryDto;
import com.example.springbootbackend.dto.assembler.CountryDtoAssembler;
import com.example.springbootbackend.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryDtoAssembler countryDtoAssembler;

    @GetMapping(value = "/{countryId}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Integer countryId) {
        Country country = countryService.findById(countryId);
        CountryDto countryDto = countryDtoAssembler.toModel(country);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CountryDto>> getAllCountries() {
        List<Country> countries = countryService.findAll();
        CollectionModel<CountryDto> countryDtos = countryDtoAssembler.toCollectionModel(countries);
        return new ResponseEntity<>(countryDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CountryDto> addCountry(@RequestBody Country country) {
        Country newCountry = countryService.create(country);
        CountryDto countryDto = countryDtoAssembler.toModel(newCountry);
        return new ResponseEntity<>(countryDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{countryId}")
    public ResponseEntity<?> updateCountry(@RequestBody Country uCountry, @PathVariable Integer countryId) {
        countryService.update(countryId, uCountry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer countryId) {
        countryService.delete(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/insert_10_countries/{countryName}")
    public ResponseEntity<?> insertTenCountries(@PathVariable String countryName){
        countryService.insertCountries(countryName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/insert_into_country/{countryName}")
    public ResponseEntity<?> insertIntoCountry(@PathVariable String countryName){
        countryService.insertIntoCountry(countryName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
