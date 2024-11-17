package com.maiphong.taskmanagement.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maiphong.taskmanagement.dtos.notification.NotificationCreateUpdateDTO;
import com.maiphong.taskmanagement.dtos.notification.NotificationDTO;
import com.maiphong.taskmanagement.services.NotificationService;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotification() {
        List<NotificationDTO> notificationDTOs = notificationService.getAll();

        if (notificationDTOs == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(notificationDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getById(@PathVariable UUID id) {
        NotificationDTO notificationDTO = notificationService.getById(id);

        if (notificationDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificationDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody NotificationCreateUpdateDTO notificationCreateDTO) {
        boolean isCreated = notificationService.create(notificationCreateDTO);

        if (!isCreated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(isCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,
            @RequestBody NotificationCreateUpdateDTO notificationCreateDTO) {
        boolean isUpdated = notificationService.update(id, notificationCreateDTO);

        if (!isUpdated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(isUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        boolean isDeleted = notificationService.delete(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(isDeleted);
    }

}
