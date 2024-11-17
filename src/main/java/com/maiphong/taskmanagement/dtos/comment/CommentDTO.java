package com.maiphong.taskmanagement.dtos.comment;

import java.time.LocalDate;

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
    private LocalDate createAt;
}
