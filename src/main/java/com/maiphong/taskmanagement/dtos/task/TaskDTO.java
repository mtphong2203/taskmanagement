package com.maiphong.taskmanagement.dtos.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    @NotNull(message = "Title required")
    private String title;

    @Length(message = "500 characters is maximum")
    private String description;

    @NotNull(message = "Due date is required")
    private LocalDateTime dueDate;

    @NotNull(message = "Priority is required")
    private String priority;

    @NotNull(message = "status is required")
    private String status;

    @NotNull(message = "project name should not be null")
    private String projectName;
}
