package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.taskmanagement.dtos.role.RoleDTO;

public interface RoleService {
    List<RoleDTO> getAll();

    RoleDTO getById(UUID id);

    boolean create(RoleDTO roleDTO);

    boolean update(UUID id, RoleDTO roleDTO);

    boolean delete(UUID id);
}
