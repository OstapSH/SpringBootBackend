package com.example.springbootbackend.exception;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException(Integer id){
        super("Could not find 'place' with id=" + id);
    }
}
