package com.maiphong.taskmanagement.dtos.role;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateDTO {

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is not empty")
    @Length(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @Length(max = 255, message = "Description maximum is 255 characters")
    private String description;

    private UUID userId;

}
