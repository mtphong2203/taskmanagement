package com.maiphong.taskmanagement.dtos.notification;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    @Length(max = 100, message = "100 characters is maximum")
    private String message;

    @NotNull(message = "create time is required")
    private LocalDateTime createAt;

    @NotNull(message = "status is required")
    private String status;

    private String taskName;

}
