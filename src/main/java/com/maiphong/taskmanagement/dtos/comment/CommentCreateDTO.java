package com.maiphong.taskmanagement.dtos.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDTO {
    @NotNull(message = "Title required")
    private String content;

    @NotNull(message = "time create should be exist!")
    private LocalDateTime createAt;

    @NotNull(message = "not null")
    private UUID taskId;

}
