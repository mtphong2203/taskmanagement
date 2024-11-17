package com.maiphong.taskmanagement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maiphong.taskmanagement.dtos.comment.CommentCreateDTO;
import com.maiphong.taskmanagement.dtos.comment.CommentDTO;
import com.maiphong.taskmanagement.entities.Comment;
import com.maiphong.taskmanagement.entities.Task;
import com.maiphong.taskmanagement.exceptions.ResourceNotFoundException;
import com.maiphong.taskmanagement.repositories.CommentRepository;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentDTO> getAll() {
        List<Comment> comments = commentRepository.findAll();
        // Convert to list DTO
        List<CommentDTO> commentDTOs = comments.stream().map(comment -> {
            // do one by one
            CommentDTO commentDTO = new CommentDTO();

            commentDTO.setContent(comment.getContent());
            commentDTO.setCreateAt(comment.getCreateAt());

            if (comment.getTask() != null) {
                commentDTO.setTaskName(comment.getTask().getTitle());
            }

            return commentDTO;
        }).toList();
        return commentDTOs;
    }

    @Override
    public CommentDTO getById(UUID id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null) {
            throw new ResourceNotFoundException("Comment is not found");
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setContent(comment.getContent());
        commentDTO.setCreateAt(comment.getCreateAt());

        if (comment.getTask() != null) {
            commentDTO.setTaskName(comment.getTask().getTitle());
        }

        return commentDTO;
    }

    @Override
    public boolean create(CommentCreateDTO commentCreateDTO) {
        if (commentCreateDTO == null) {
            throw new IllegalArgumentException("Comment is required");
        }

        Comment newComment = new Comment();

        newComment.setContent(commentCreateDTO.getContent());
        newComment.setCreateAt(LocalDate.now());

        if (commentCreateDTO.getTaskId() != null) {
            Task task = new Task();
            task.setId(commentCreateDTO.getTaskId());
            newComment.setTask(task);
        }

        newComment = commentRepository.save(newComment);

        return newComment != null;
    }

    @Override
    public boolean update(UUID id, CommentCreateDTO commentCreateDTO) {
        if (commentCreateDTO == null) {
            throw new IllegalArgumentException("Comment is required");
        }

        Comment newComment = commentRepository.findById(id).orElse(null);

        if (newComment == null) {
            throw new IllegalArgumentException("Comment title is not exist!");
        }

        newComment.setContent(commentCreateDTO.getContent());
        newComment.setCreateAt(LocalDate.now());
        if (commentCreateDTO.getTaskId() != null) {
            Task task = new Task();
            task.setId(commentCreateDTO.getTaskId());
            newComment.setTask(task);
        }

        newComment = commentRepository.save(newComment);

        return newComment != null;
    }

    @Override
    public boolean delete(UUID id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null) {
            throw new IllegalArgumentException("Comment title is not exist!");
        }

        commentRepository.delete(comment);

        return !commentRepository.existsById(id);
    }

}
