package com.example.springbootbackend.controller;

import com.example.springbootbackend.domain.Place;
import com.example.springbootbackend.dto.PlaceDto;
import com.example.springbootbackend.dto.assembler.PlaceDtoAssembler;
import com.example.springbootbackend.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceDtoAssembler placeDtoAssembler;

    @GetMapping(value = "/{placeId}")
    public ResponseEntity<PlaceDto> getPlace(@PathVariable Integer placeId) {
        Place place = placeService.findById(placeId);
        PlaceDto placeDto = placeDtoAssembler.toModel(place);
        return new ResponseEntity<>(placeDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<PlaceDto>> getAllPlaces() {
        List<Place> places = placeService.findAll();
        CollectionModel<PlaceDto> placeDtos = placeDtoAssembler.toCollectionModel(places);
        return new ResponseEntity<>(placeDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<PlaceDto> addPlace(@RequestBody Place place) {
        Place newPlace = placeService.create(place);
        PlaceDto placeDto = placeDtoAssembler.toModel(newPlace);
        return new ResponseEntity<>(placeDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{placeId}")
    public ResponseEntity<?> updatePlace(@RequestBody Place uPlace, @PathVariable Integer placeId) {
        placeService.update(placeId, uPlace);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{placeId}")
    public ResponseEntity<?> deletePlace(@PathVariable Integer placeId) {
        placeService.delete(placeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/min_place_price")
    public int getMinPlacePrice(){
        return placeService.getMinPlacePrice();
    }
}
