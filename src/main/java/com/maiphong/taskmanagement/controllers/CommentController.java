package com.maiphong.taskmanagement.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maiphong.taskmanagement.dtos.comment.CommentCreateDTO;
import com.maiphong.taskmanagement.dtos.comment.CommentDTO;
import com.maiphong.taskmanagement.services.CommentService;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComment() {
        List<CommentDTO> commentDTOs = commentService.getAll();

        if (commentDTOs == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(commentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getById(@PathVariable UUID id) {
        CommentDTO commentDTO = commentService.getById(id);

        if (commentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CommentCreateDTO commentCreateDTO) {
        boolean isCreated = commentService.create(commentCreateDTO);

        if (!isCreated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(isCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody CommentCreateDTO commentCreateDTO) {
        boolean isUpdated = commentService.update(id, commentCreateDTO);

        if (!isUpdated) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(isUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        boolean isDeleted = commentService.delete(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(isDeleted);
    }

}
