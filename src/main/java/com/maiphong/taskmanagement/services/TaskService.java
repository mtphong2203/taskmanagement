package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.taskmanagement.dtos.task.TaskCreateUpdateDTO;
import com.maiphong.taskmanagement.dtos.task.TaskDTO;

public interface TaskService {
    List<TaskDTO> getAll();

    TaskDTO getById(UUID id);

    boolean create(TaskCreateUpdateDTO taskCreateUpdateDTO);

    boolean update(UUID id, TaskCreateUpdateDTO TaskCreateUpdateDTO);

    boolean delete(UUID id);

}
