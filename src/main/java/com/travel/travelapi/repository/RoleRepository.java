package com.travel.travelapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travelapi.entity.Role;
import com.travel.travelapi.enums.ERole;


public interface RoleRepository extends JpaRepository<Role, Integer>{

    Optional<Role> findByName(ERole name);
}
