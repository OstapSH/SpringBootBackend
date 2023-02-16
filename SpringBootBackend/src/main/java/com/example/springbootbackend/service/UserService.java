package com.example.springbootbackend.service;

import com.example.springbootbackend.domain.Place;
import com.example.springbootbackend.domain.User;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface UserService extends GeneralService<User, Integer> {
    User create(User user, Integer countryId);
    List<Place> findPlacesById(Integer id);

    void addUserPlace(Integer userId, Integer placeId);

    void createTables();
}
