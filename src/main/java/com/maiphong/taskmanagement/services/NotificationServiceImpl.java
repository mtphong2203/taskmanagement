package com.maiphong.taskmanagement.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maiphong.taskmanagement.dtos.notification.NotificationCreateUpdateDTO;
import com.maiphong.taskmanagement.dtos.notification.NotificationDTO;
import com.maiphong.taskmanagement.entities.Comment;
import com.maiphong.taskmanagement.entities.Notification;
import com.maiphong.taskmanagement.entities.StatusNotification;
import com.maiphong.taskmanagement.entities.Task;
import com.maiphong.taskmanagement.exceptions.ResourceNotFoundException;
import com.maiphong.taskmanagement.repositories.NotificationRepository;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<NotificationDTO> getAll() {
        List<Notification> notifications = notificationRepository.findAll();
        // Convert to list DTO
        List<NotificationDTO> notificationDTOs = notifications.stream().map(notification -> {
            // do one by one
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setMessage(notification.getMessage());
            notification.setCreateAt(notification.getCreateAt());
            notification.setStatus(notification.getStatus());

            if (notification.getTaskNotification() != null) {
                notificationDTO.setTaskName(notification.getTaskNotification().getTitle());
            }

            return notificationDTO;
        }).toList();
        return notificationDTOs;
    }

    @Override
    public NotificationDTO getById(UUID id) {
        Notification notification = notificationRepository.findById(id).orElse(null);

        if (notification == null) {
            throw new ResourceNotFoundException("Notification is not found");
        }

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage(notification.getMessage());
        notification.setCreateAt(notification.getCreateAt());
        notification.setStatus(notification.getStatus());

        if (notification.getTaskNotification() != null) {
            notificationDTO.setTaskName(notification.getTaskNotification().getTitle());
        }

        return notificationDTO;
    }

    @Override
    public boolean create(NotificationCreateUpdateDTO notificationCreateUpdateDTO) {
        if (notificationCreateUpdateDTO == null) {
            throw new IllegalArgumentException("Notification is required");
        }

        Notification newNotification = new Notification();

        newNotification.setMessage(notificationCreateUpdateDTO.getMessage());
        newNotification.setCreateAt(LocalDateTime.now());
        newNotification.setStatus(StatusNotification.valueOf(notificationCreateUpdateDTO.getStatus().toUpperCase()));

        if (notificationCreateUpdateDTO.getTaskId() != null || notificationCreateUpdateDTO.getCommentId() != null) {
            Task task = new Task();
            task.setId(notificationCreateUpdateDTO.getTaskId());
            Comment comment = new Comment();
            comment.setId(notificationCreateUpdateDTO.getCommentId());

            newNotification.setTaskNotification(task);
            newNotification.setComment(comment);
        }

        newNotification = notificationRepository.save(newNotification);

        return newNotification != null;
    }

    @Override
    public boolean update(UUID id, NotificationCreateUpdateDTO notificationCreateUpdateDTO) {
        if (notificationCreateUpdateDTO == null) {
            throw new IllegalArgumentException("Notification is required");
        }

        Notification newNotification = notificationRepository.findById(id).orElse(null);

        if (newNotification == null) {
            throw new IllegalArgumentException("Notification title is not exist!");
        }

        newNotification.setMessage(notificationCreateUpdateDTO.getMessage());
        newNotification.setCreateAt(LocalDateTime.now());
        newNotification.setStatus(StatusNotification.valueOf(notificationCreateUpdateDTO.getStatus().toUpperCase()));

        if (notificationCreateUpdateDTO.getTaskId() != null || notificationCreateUpdateDTO.getCommentId() != null) {
            Task task = new Task();
            task.setId(notificationCreateUpdateDTO.getTaskId());
            Comment comment = new Comment();
            comment.setId(notificationCreateUpdateDTO.getCommentId());

            newNotification.setTaskNotification(task);
            newNotification.setComment(comment);
        }

        newNotification = notificationRepository.save(newNotification);

        return newNotification != null;
    }

    @Override
    public boolean delete(UUID id) {
        Notification notification = notificationRepository.findById(id).orElse(null);

        if (notification == null) {
            throw new IllegalArgumentException("Notification title is not exist!");
        }

        notificationRepository.delete(notification);

        return !notificationRepository.existsById(id);
    }

}
