package com.maiphong.taskmanagement.services;

import java.util.List;
import java.util.UUID;

import com.maiphong.taskmanagement.dtos.comment.CommentDTO;

public interface CommentService {
    List<CommentDTO> getAll();

    CommentDTO getById(UUID id);

    boolean create(CommentDTO commentDTO);

    boolean update(UUID id, CommentDTO commentDTO);

    boolean delete(UUID id);

}