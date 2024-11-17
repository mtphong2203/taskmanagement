package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.taskmanagement.dtos.project.ProjectDTO;

public interface ProjectService {
    List<ProjectDTO> getAll();

    ProjectDTO getById(UUID id);

    boolean create(ProjectDTO taskDTO);

    boolean update(UUID id, ProjectDTO taskDTO);

    boolean delete(UUID id);

}
