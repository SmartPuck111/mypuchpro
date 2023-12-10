package com.blog.service;

import java.util.List;

import com.blog.exception.LoginException;
import com.blog.exception.PostException;
import com.blog.exception.UserException;
import com.blog.model.Post;
import com.blog.model.PostDTO;
import com.blog.model.PostUpdateDTO;

public interface PostService {

	public List<Post> getAllPost() throws PostException, LoginException;

	public Post getPostById(Integer postId) throws PostException, LoginException;

	public Post createNewPost(PostDTO post) throws PostException, LoginException, UserException;

	public Post updatePost(PostUpdateDTO post, Integer postId) throws PostException, LoginException, UserException;

	public Post deletePostById(Integer postId) throws PostException, LoginException;

	public List<Post> pagination(Integer pageSize, String sortBy) throws PostException, LoginException;
}
