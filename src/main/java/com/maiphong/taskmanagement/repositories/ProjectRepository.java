package com.maiphong.taskmanagement.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maiphong.taskmanagement.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Project findByName(String name);
}
