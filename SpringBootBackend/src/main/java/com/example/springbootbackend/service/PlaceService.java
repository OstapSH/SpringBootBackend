package com.example.springbootbackend.service;

import com.example.springbootbackend.domain.Place;

public interface PlaceService extends GeneralService<Place, Integer>{
    int getMinPlacePrice();
}
