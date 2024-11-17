package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.taskmanagement.dtos.comment.CommentCreateDTO;
import com.maiphong.taskmanagement.dtos.comment.CommentDTO;

public interface CommentService {
    List<CommentDTO> getAll();

    CommentDTO getById(UUID id);

    boolean create(CommentCreateDTO commentCreateDTO);

    boolean update(UUID id, CommentCreateDTO commentCreateDTO);

    boolean delete(UUID id);

}