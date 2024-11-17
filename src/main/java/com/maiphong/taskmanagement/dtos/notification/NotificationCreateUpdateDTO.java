package com.maiphong.taskmanagement.dtos.notification;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateUpdateDTO {
    @Length(max = 100, message = "100 characters is maximum")
    private String message;

    @NotNull(message = "create time is required")
    private LocalDateTime createAt;

    @NotNull(message = "status is required")
    private String status;

    private UUID taskId;

    private UUID commentId;

}
