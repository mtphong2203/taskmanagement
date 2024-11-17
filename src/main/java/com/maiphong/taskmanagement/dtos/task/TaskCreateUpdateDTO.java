package com.maiphong.taskmanagement.dtos.task;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateUpdateDTO {
    @NotNull(message = "Title required")
    private String title;

    @Length(message = "500 characters is maximum")
    private String description;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @NotNull(message = "Priority is required")
    private String priority;

    @NotNull(message = "status is required")
    private String status;

    @NotNull(message = "project id should not be null")
    private UUID projectId;
}
