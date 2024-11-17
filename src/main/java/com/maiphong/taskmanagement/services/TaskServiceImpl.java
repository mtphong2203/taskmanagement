package com.maiphong.taskmanagement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maiphong.taskmanagement.dtos.task.TaskDTO;
import com.maiphong.taskmanagement.entities.Priority;
import com.maiphong.taskmanagement.entities.Status;
import com.maiphong.taskmanagement.entities.Task;
import com.maiphong.taskmanagement.exceptions.ResourceNotFoundException;
import com.maiphong.taskmanagement.repositories.TaskRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDTO> getAll() {
        List<Task> tasks = taskRepository.findAll();
        // Convert to list DTO
        List<TaskDTO> taskDTOs = tasks.stream().map(task -> {
            // do one by one
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDueDate(task.getDueDate());
            // get enum value and convert to string to show
            taskDTO.setPriority(task.getPriority().toString());
            taskDTO.setStatus(task.getStatus().toString());
            return taskDTO;
        }).toList();
        return taskDTOs;
    }

    @Override
    public TaskDTO getById(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);

        if (task == null) {
            throw new ResourceNotFoundException("Task is not found");
        }

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());
        // get enum value then convert to string to show
        taskDTO.setPriority(task.getPriority().toString());
        taskDTO.setStatus(task.getStatus().toString());
        return taskDTO;
    }

    @Override
    public boolean create(TaskDTO taskDTO) {
        if (taskDTO == null) {
            throw new IllegalArgumentException("Task is required");
        }

        Task task = taskRepository.findByTitle(taskDTO.getTitle());

        if (task != null) {
            throw new IllegalArgumentException("Task title is exist!");
        }

        Task newTask = new Task();
        newTask.setTitle(taskDTO.getTitle());
        newTask.setDescription(taskDTO.getDescription());
        newTask.setDueDate(taskDTO.getDueDate());
        // input string value , need to convert to enum save to database
        newTask.setPriority(Priority.valueOf(taskDTO.getPriority().toUpperCase()));
        newTask.setStatus(Status.valueOf(taskDTO.getStatus().toUpperCase()));
        newTask.setCreateAt(LocalDate.now());
        newTask = taskRepository.save(newTask);

        return newTask != null;
    }

    @Override
    public boolean update(UUID id, TaskDTO taskDTO) {
        if (taskDTO == null) {
            throw new IllegalArgumentException("Task is required");
        }

        Task task = taskRepository.findById(id).orElse(null);

        if (task == null) {
            throw new IllegalArgumentException("Task title is not exist!");
        }

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        // input string value , need to convert to enum save to database
        task.setPriority(Priority.valueOf(taskDTO.getPriority().toUpperCase()));
        task.setStatus(Status.valueOf(taskDTO.getStatus().toUpperCase()));
        task.setUpdateAt(LocalDate.now());

        task = taskRepository.save(task);

        return task != null;
    }

    @Override
    public boolean delete(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);

        if (task == null) {
            throw new IllegalArgumentException("Task title is not exist!");
        }

        taskRepository.delete(task);

        return !taskRepository.existsById(id);
    }

}
