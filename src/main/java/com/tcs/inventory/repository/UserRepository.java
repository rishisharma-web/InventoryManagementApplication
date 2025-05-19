package com.tcs.inventory.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.inventory.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
