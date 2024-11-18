package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.taskmanagement.dtos.role.RoleCreateDTO;
import com.maiphong.taskmanagement.dtos.role.RoleDTO;
import com.maiphong.taskmanagement.dtos.role.RoleUpdateDTO;

public interface RoleService {
    List<RoleDTO> getAll();

    RoleDTO getById(UUID id);

    boolean create(RoleCreateDTO roleCreateDTO);

    boolean update(UUID id, RoleUpdateDTO roleUpdateDTO);

    boolean delete(UUID id);
}
