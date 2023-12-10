package com.blog.service;

import java.util.List;

import com.blog.exception.CommentException;
import com.blog.exception.LoginException;
import com.blog.exception.PostException;
import com.blog.model.Comment;
import com.blog.model.CommentDTO;
import com.blog.model.CommentUpdateDTO;

public interface CommentService {

	public List<Comment> getAllComment(Integer postId) throws PostException, CommentException, LoginException;

	public Comment getCommentById(Integer postId, Integer commentId)
			throws PostException, LoginException, CommentException;

	public Comment createNewComment(Integer postId, CommentDTO comment)
			throws PostException, LoginException, CommentException;

	public Comment updateComment(Integer postId, CommentUpdateDTO comment, Integer commentId)
			throws PostException, LoginException, CommentException;

	public Comment deleteCommentById(Integer postId, Integer commentId)
			throws PostException, LoginException, CommentException;

}
