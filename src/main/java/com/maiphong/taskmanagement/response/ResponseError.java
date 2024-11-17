package com.maiphong.taskmanagement.response;

import org.springframework.http.HttpStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private String message;

    private HttpStatus status;
}
