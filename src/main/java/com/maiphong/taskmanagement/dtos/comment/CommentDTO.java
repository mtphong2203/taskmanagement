package com.maiphong.taskmanagement.dtos.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    @NotNull(message = "Title required")
    private String content;

    @NotNull(message = "time create should be exist!")
    private LocalDateTime createAt;

    private String taskName;
}
