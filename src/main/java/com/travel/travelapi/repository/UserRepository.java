package com.travel.travelapi.repository;

import com.travel.travelapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    boolean existsByEmail(String email);

    boolean existsByFirstNameAndLastNameAndIdNot(String firstName, String lastName, Integer id);
}
