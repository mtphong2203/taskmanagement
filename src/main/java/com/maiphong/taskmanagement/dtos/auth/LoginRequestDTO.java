package com.maiphong.taskmanagement.dtos.auth;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "Can not contain blank")
    @Length(min = 3, max = 20, message = "user name has 3 to 20 characters")
    private String username;

    @NotBlank(message = "Can not contain blank in password")
    @NotNull(message = "Can not null")
    @Length(min = 5, max = 30, message = "password has 5 to 30 characters")
    private String password;
}
