package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.taskmanagement.dtos.notification.NotificationCreateUpdateDTO;
import com.maiphong.taskmanagement.dtos.notification.NotificationDTO;

public interface NotificationService {
    List<NotificationDTO> getAll();

    NotificationDTO getById(UUID id);

    boolean create(NotificationCreateUpdateDTO notificationCreateUpdateDTO);

    boolean update(UUID id, NotificationCreateUpdateDTO notificationCreateUpdateDTO);

    boolean delete(UUID id);
}
