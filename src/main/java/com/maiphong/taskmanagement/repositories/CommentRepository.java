package com.maiphong.taskmanagement.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maiphong.taskmanagement.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

}
