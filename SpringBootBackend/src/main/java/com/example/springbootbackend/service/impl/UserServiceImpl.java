package com.example.springbootbackend.service.impl;

import com.example.springbootbackend.domain.Country;
import com.example.springbootbackend.domain.Place;
import com.example.springbootbackend.domain.User;
import com.example.springbootbackend.exception.CountryNotFoundException;
import com.example.springbootbackend.exception.UserNotFoundException;
import com.example.springbootbackend.repository.CountryRepository;
import com.example.springbootbackend.repository.PlaceRepository;
import com.example.springbootbackend.repository.UserRepository;
import com.example.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    PlaceRepository placeRepository;

    public List<Place> findPlacesById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return user.getPlaces().stream().toList();
    }


    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User create(User user, Integer countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));
        user.setCountry(country);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void update(Integer userId, User uUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.setName(uUser.getName());
        user.setEmail(uUser.getEmail());
        user.setCountry(uUser.getCountry());
        userRepository.save(user);
    }

    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    @Transactional
    public void addUserPlace(Integer userId, Integer placeId) {
        userRepository.addUserPlace(userId, placeId);
    }

    @Transactional
    public void createTables() {
        userRepository.createTables();
    }
}
