package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maiphong.taskmanagement.dtos.role.RoleCreateDTO;
import com.maiphong.taskmanagement.dtos.role.RoleDTO;
import com.maiphong.taskmanagement.dtos.role.RoleUpdateDTO;
import com.maiphong.taskmanagement.entities.Role;
import com.maiphong.taskmanagement.entities.User;
import com.maiphong.taskmanagement.exceptions.ResourceNotFoundException;
import com.maiphong.taskmanagement.repositories.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> getAll() {
        List<Role> roles = roleRepository.findAll();
        // Convert to list DTO
        List<RoleDTO> roleDTOs = roles.stream().map(role -> {
            // do one by one
            RoleDTO roleDTO = new RoleDTO();

            roleDTO.setName(role.getName());
            roleDTO.setDescription(role.getDescription());

            if (role.getUser() != null) {
                roleDTO.setUsername(role.getUser().getUsername());
            }

            return roleDTO;
        }).toList();
        return roleDTOs;
    }

    @Override
    public RoleDTO getById(UUID id) {
        Role role = roleRepository.findById(id).orElse(null);

        if (role == null) {
            throw new ResourceNotFoundException("Role is not found");
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());

        if (role.getUser() != null) {
            roleDTO.setUsername(role.getUser().getUsername());
        }

        return roleDTO;
    }

    @Override
    public boolean create(RoleCreateDTO roleCreateDTO) {
        if (roleCreateDTO == null) {
            throw new IllegalArgumentException("Role is required");
        }

        Role newRole = new Role();
        newRole.setName(roleCreateDTO.getName().toUpperCase());
        newRole.setDescription(roleCreateDTO.getDescription().toUpperCase());

        if (roleCreateDTO.getUserId() != null) {
            User user = new User();
            user.setId(roleCreateDTO.getUserId());
            newRole.setUser(user);
        }

        newRole = roleRepository.save(newRole);

        return newRole != null;
    }

    @Override
    public boolean update(UUID id, RoleUpdateDTO roleUpdateDTO) {
        if (roleUpdateDTO == null) {
            throw new IllegalArgumentException("Role is required");
        }

        Role newRole = roleRepository.findById(id).orElse(null);

        if (newRole == null) {
            throw new IllegalArgumentException("Role title is not exist!");
        }

        newRole.setName(roleUpdateDTO.getName().toUpperCase());
        newRole.setDescription(roleUpdateDTO.getDescription().toUpperCase());

        if (roleUpdateDTO.getUserId() != null) {
            User user = new User();
            user.setId(roleUpdateDTO.getUserId());
            newRole.setUser(user);
        }

        newRole = roleRepository.save(newRole);

        return newRole != null;
    }

    @Override
    public boolean delete(UUID id) {
        Role role = roleRepository.findById(id).orElse(null);

        if (role == null) {
            throw new IllegalArgumentException("Role is not exist!");
        }

        roleRepository.delete(role);

        return !roleRepository.existsById(id);
    }

}
