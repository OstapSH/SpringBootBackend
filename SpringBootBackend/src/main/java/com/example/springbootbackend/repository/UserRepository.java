package com.example.springbootbackend.repository;

import com.example.springbootbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Procedure
    void addUserPlace(Integer userId, Integer placeId);

    @Procedure
    void createTables();
}
