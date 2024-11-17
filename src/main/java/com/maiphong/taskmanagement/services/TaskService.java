package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.taskmanagement.dtos.task.TaskDTO;

public interface TaskService {
    List<TaskDTO> getAll();

    TaskDTO getById(UUID id);

    boolean create(TaskDTO taskDTO);

    boolean update(UUID id, TaskDTO taskDTO);

    boolean delete(UUID id);

}
