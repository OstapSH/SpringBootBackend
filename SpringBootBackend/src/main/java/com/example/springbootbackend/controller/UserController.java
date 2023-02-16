package com.example.springbootbackend.controller;

import com.example.springbootbackend.domain.Place;
import com.example.springbootbackend.domain.User;
import com.example.springbootbackend.dto.PlaceDto;
import com.example.springbootbackend.dto.UserDto;
import com.example.springbootbackend.dto.assembler.PlaceDtoAssembler;
import com.example.springbootbackend.dto.assembler.UserDtoAssembler;
import com.example.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoAssembler userDtoAssembler;
    @Autowired
    private PlaceDtoAssembler placeDtoAssembler;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        UserDto userDto = userDtoAssembler.toModel(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/places")
    public ResponseEntity<CollectionModel<PlaceDto>> getAllPlacesForUser(@PathVariable Integer userId) {
        List<Place> places = userService.findPlacesById(userId);
        Link selfLink = linkTo(methodOn(UserController.class).getAllPlacesForUser(userId)).withSelfRel();
        CollectionModel<PlaceDto> placeDtos = placeDtoAssembler.toCollectionModel(places, selfLink);
        return new ResponseEntity<>(placeDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDto> addUser(@RequestBody User user) {
        User newUser = userService.create(user);
        UserDto userDto = userDtoAssembler.toModel(newUser);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{countryId}")
    public ResponseEntity<UserDto> addUserWithCountry(@RequestBody User user, @PathVariable Integer countryId) {
        User newUser = userService.create(user, countryId);
        UserDto userDto = userDtoAssembler.toModel(newUser);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User uUser, @PathVariable Integer userId) {
        userService.update(userId, uUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        CollectionModel<UserDto> userDtos = userDtoAssembler.toCollectionModel(users);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/{placeId}")
    public ResponseEntity<?> addUserPlace(@PathVariable Integer userId, @PathVariable Integer placeId) {
        userService.addUserPlace(userId, placeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/createTables")
    public ResponseEntity<?> createTables() {
        userService.createTables();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
