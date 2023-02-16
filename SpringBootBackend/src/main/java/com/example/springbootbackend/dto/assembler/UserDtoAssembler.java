package com.example.springbootbackend.dto.assembler;

import com.example.springbootbackend.controller.UserController;
import com.example.springbootbackend.domain.User;
import com.example.springbootbackend.dto.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserDtoAssembler implements RepresentationModelAssembler<User, UserDto> {
    @Override
    public UserDto toModel(User entity) {
        UserDto userDto = UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .country(entity.getCountry().getName())
                .build();
        Link selfLink = linkTo(methodOn(UserController.class).getUser(userDto.getId())).withSelfRel();
        userDto.add(selfLink);
        Link bookLink = linkTo(methodOn(UserController.class).getAllPlacesForUser(entity.getId())).withRel("books");
        userDto.add(bookLink);
        return userDto;
    }

    @Override
    public CollectionModel<UserDto> toCollectionModel(Iterable<? extends User> entities) {
        CollectionModel<UserDto> userDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();
        userDtos.add(selfLink);
        return userDtos;
    }

    public CollectionModel<UserDto> toCollectionModel(Iterable<? extends User> entities, Link link) {
        CollectionModel<UserDto> userDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        userDtos.add(link);
        return userDtos;
    }
}
