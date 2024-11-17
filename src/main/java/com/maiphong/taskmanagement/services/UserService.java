package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.taskmanagement.dtos.user.UserCreateDTO;
import com.maiphong.taskmanagement.dtos.user.UserDTO;
import com.maiphong.taskmanagement.dtos.user.UserEditDTO;

public interface UserService {
    List<UserDTO> getAll();

    UserDTO getById(UUID id);

    boolean create(UserCreateDTO userCreateDTO);

    boolean update(UUID id, UserEditDTO userEditDTO);

    boolean delete(UUID id);
}
