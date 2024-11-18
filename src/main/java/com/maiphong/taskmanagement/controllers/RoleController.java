package com.maiphong.taskmanagement.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maiphong.taskmanagement.dtos.role.RoleCreateDTO;
import com.maiphong.taskmanagement.dtos.role.RoleDTO;
import com.maiphong.taskmanagement.dtos.role.RoleUpdateDTO;
import com.maiphong.taskmanagement.services.RoleService;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRole() {
        List<RoleDTO> roleDTOs = roleService.getAll();

        if (roleDTOs == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(roleDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getById(@PathVariable UUID id) {
        RoleDTO roleDTO = roleService.getById(id);

        if (roleDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RoleCreateDTO roleCreateDTO) {
        boolean isCreated = roleService.create(roleCreateDTO);

        if (!isCreated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(isCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody RoleUpdateDTO roleUpdateDTO) {
        boolean isUpdated = roleService.update(id, roleUpdateDTO);

        if (!isUpdated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(isUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        boolean isDeleted = roleService.delete(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(isDeleted);
    }

}
