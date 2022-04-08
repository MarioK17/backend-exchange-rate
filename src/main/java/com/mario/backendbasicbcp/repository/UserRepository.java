package com.mario.backendbasicbcp.repository;

import com.mario.backendbasicbcp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where u.username = :username " +
            "and u.password = :password ")
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("select u from User u " +
            "join fetch u.roles r " +
            "where u.username = :username ")
    Optional<User> findByUsername(String username);

}
