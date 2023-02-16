package com.example.springbootbackend.dto.assembler;

import com.example.springbootbackend.controller.PlaceController;
import com.example.springbootbackend.domain.Place;
import com.example.springbootbackend.dto.PlaceDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlaceDtoAssembler implements RepresentationModelAssembler<Place, PlaceDto> {
    @Override
    public PlaceDto toModel(Place entity) {
        PlaceDto placeDto = PlaceDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(PlaceController.class).getPlace(placeDto.getId())).withSelfRel();
        placeDto.add(selfLink);
        return placeDto;
    }

    @Override
    public CollectionModel<PlaceDto> toCollectionModel(Iterable<? extends Place> entities) {
        CollectionModel<PlaceDto> placeDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(PlaceController.class).getAllPlaces()).withSelfRel();
        placeDtos.add(selfLink);
        return placeDtos;
    }

    public CollectionModel<PlaceDto> toCollectionModel(Iterable<? extends Place> entities, Link link) {
        CollectionModel<PlaceDto> placeDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        placeDtos.add(link);
        return placeDtos;
    }
}
