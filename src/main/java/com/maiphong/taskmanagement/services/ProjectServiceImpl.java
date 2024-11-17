package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maiphong.taskmanagement.dtos.project.ProjectDTO;
import com.maiphong.taskmanagement.entities.Project;
import com.maiphong.taskmanagement.entities.Status;
import com.maiphong.taskmanagement.exceptions.ResourceNotFoundException;
import com.maiphong.taskmanagement.repositories.ProjectRepository;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDTO> getAll() {
        List<Project> projects = projectRepository.findAll();
        // Convert to list DTO
        List<ProjectDTO> projectDTOs = projects.stream().map(project -> {
            // do one by one
            ProjectDTO projectDTO = new ProjectDTO();

            projectDTO.setName(project.getName());
            projectDTO.setDescription(project.getDescription());
            projectDTO.setStartDate(project.getStartDate());
            projectDTO.setEndDate(project.getEndDate());
            projectDTO.setStatus(project.getStatus().toString());

            return projectDTO;
        }).toList();
        return projectDTOs;
    }

    @Override
    public ProjectDTO getById(UUID id) {
        Project project = projectRepository.findById(id).orElse(null);

        if (project == null) {
            throw new ResourceNotFoundException("Project is not found");
        }

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setEndDate(project.getEndDate());
        projectDTO.setStatus(project.getStatus().toString());

        return projectDTO;
    }

    @Override
    public boolean create(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            throw new IllegalArgumentException("Project is required");
        }

        Project project = projectRepository.findByName(projectDTO.getName());

        if (project != null) {
            throw new IllegalArgumentException("Project title is exist!");
        }

        Project newProject = new Project();
        newProject.setName(projectDTO.getName());
        newProject.setDescription(projectDTO.getDescription());
        newProject.setStartDate(projectDTO.getStartDate());
        newProject.setEndDate(projectDTO.getEndDate());
        newProject.setStatus(Status.valueOf(projectDTO.getStatus().toUpperCase()));

        newProject = projectRepository.save(newProject);

        return newProject != null;
    }

    @Override
    public boolean update(UUID id, ProjectDTO projectDTO) {
        if (projectDTO == null) {
            throw new IllegalArgumentException("Project is required");
        }

        Project newProject = projectRepository.findById(id).orElse(null);

        if (newProject == null) {
            throw new IllegalArgumentException("Project title is not exist!");
        }

        newProject.setName(projectDTO.getName());
        newProject.setDescription(projectDTO.getDescription());
        newProject.setStartDate(projectDTO.getStartDate());
        newProject.setEndDate(projectDTO.getEndDate());
        newProject.setStatus(Status.valueOf(projectDTO.getStatus().toUpperCase()));

        newProject = projectRepository.save(newProject);

        return newProject != null;
    }

    @Override
    public boolean delete(UUID id) {
        Project project = projectRepository.findById(id).orElse(null);

        if (project == null) {
            throw new IllegalArgumentException("Project title is not exist!");
        }

        projectRepository.delete(project);

        return !projectRepository.existsById(id);
    }

}
