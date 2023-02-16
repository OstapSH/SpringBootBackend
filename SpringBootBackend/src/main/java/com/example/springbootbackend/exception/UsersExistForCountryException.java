package com.example.springbootbackend.exception;

public class UsersExistForCountryException extends RuntimeException {
    public UsersExistForCountryException(Integer id) {
        super("There are available users for 'country' with id=" + id);
    }
}
