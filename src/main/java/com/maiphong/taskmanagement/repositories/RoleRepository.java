package com.maiphong.taskmanagement.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maiphong.taskmanagement.entities.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
