package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

}
