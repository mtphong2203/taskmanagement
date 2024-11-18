package com.maiphong.taskmanagement.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maiphong.taskmanagement.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
