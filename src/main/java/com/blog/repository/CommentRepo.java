package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
