package com.maiphong.taskmanagement.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maiphong.taskmanagement.dtos.task.TaskCreateUpdateDTO;
import com.maiphong.taskmanagement.dtos.task.TaskDTO;
import com.maiphong.taskmanagement.services.TaskService;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTask() {
        List<TaskDTO> taskDTOs = taskService.getAll();

        if (taskDTOs == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById(@PathVariable UUID id) {
        TaskDTO taskDTO = taskService.getById(id);

        if (taskDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TaskCreateUpdateDTO taskCreateUpdateDTO) {
        boolean isCreated = taskService.create(taskCreateUpdateDTO);

        if (!isCreated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(isCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TaskCreateUpdateDTO taskCreateUpdateDTO) {
        boolean isUpdated = taskService.update(id, taskCreateUpdateDTO);

        if (!isUpdated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(isUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        boolean isDeleted = taskService.delete(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(isDeleted);
    }

}
