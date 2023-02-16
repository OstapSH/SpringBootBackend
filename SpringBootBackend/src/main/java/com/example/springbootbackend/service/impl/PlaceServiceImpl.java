package com.example.springbootbackend.service.impl;

import com.example.springbootbackend.domain.Place;
import com.example.springbootbackend.exception.PlaceNotFoundException;
import com.example.springbootbackend.repository.PlaceRepository;
import com.example.springbootbackend.repository.UserRepository;
import com.example.springbootbackend.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    UserRepository userRepository;

    public Place findById(Integer id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Transactional
    public Place create(Place place) {
        placeRepository.save(place);
        return place;
    }

    @Transactional
    public void update(Integer id, Place uPlace) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
        place.setName(uPlace.getName());
        place.setPrice(uPlace.getPrice());
        placeRepository.save(place);
    }

    @Transactional
    public void delete(Integer id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
        placeRepository.delete(place);
    }
    @Override
    public int getMinPlacePrice() {
        return placeRepository.getMinPrice();
    };
}
