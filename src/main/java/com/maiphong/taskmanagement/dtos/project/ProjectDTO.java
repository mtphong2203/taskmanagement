package com.maiphong.taskmanagement.dtos.project;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    @NotNull(message = "Name is required")
    private String name;

    @Length(message = "500 characters is maximum")
    private String description;

    @NotNull(message = "start date is required")
    private LocalDate startDate;

    @NotNull(message = "end date is required")
    private LocalDate endDate;

    @NotNull(message = "status is required")
    private String status;

}
