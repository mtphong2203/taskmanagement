package com.maiphong.taskmanagement.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maiphong.taskmanagement.entities.Task;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Task findByTitle(String title);
}
