package com.maiphong.taskmanagement.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maiphong.taskmanagement.dtos.user.UserCreateDTO;
import com.maiphong.taskmanagement.dtos.user.UserDTO;
import com.maiphong.taskmanagement.dtos.user.UserEditDTO;
import com.maiphong.taskmanagement.services.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> userDTOs = userService.getAll();

        if (userDTOs == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable UUID id) {
        UserDTO userDTO = userService.getById(id);

        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserCreateDTO userCreateDTO) {
        boolean isCreated = userService.create(userCreateDTO);

        if (!isCreated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(isCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UserEditDTO userEditDTO) {
        boolean isUpdated = userService.update(id, userEditDTO);

        if (!isUpdated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(isUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        boolean isDeleted = userService.delete(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(isDeleted);
    }

}
