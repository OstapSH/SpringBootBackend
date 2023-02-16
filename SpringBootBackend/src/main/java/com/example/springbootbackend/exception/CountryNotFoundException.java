package com.example.springbootbackend.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Integer id) {
        super("Could not find 'country' with id=" + id);
    }
}
