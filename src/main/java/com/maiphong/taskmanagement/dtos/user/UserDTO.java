package com.maiphong.taskmanagement.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull(message = "first name is required")
    private String firstname;

    @NotNull(message = "last name is required")
    private String lastname;

    @NotNull(message = "user name is required")
    private String username;

    @NotNull(message = "email is required")
    @Email(message = "need to correct email")
    private String email;
}