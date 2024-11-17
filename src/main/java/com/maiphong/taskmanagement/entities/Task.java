package com.maiphong.taskmanagement.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "priority", nullable = false)
    private Priority priority;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "create_at", nullable = false)
    private LocalDate createAt;

    @Column(name = "update_at", unique = true)
    private LocalDate updateAt;

}
