package com.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.CommentException;
import com.blog.exception.LoginException;
import com.blog.exception.PostException;
import com.blog.model.Comment;
import com.blog.model.CommentDTO;
import com.blog.model.CommentUpdateDTO;
import com.blog.model.Login;
import com.blog.model.Post;
import com.blog.repository.CommentRepo;
import com.blog.repository.LoginRepo;
import com.blog.repository.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private LoginRepo loginRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	// checking user login validation
	public void checkLoginStatus() throws LoginException {
		List<Login> loginList = loginRepo.findAll();
		if (loginList.isEmpty())
			throw new LoginException("User login required!");
	}

	// checking valid postId
	public Post checkValidPostId(Integer postId) throws PostException {
		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isEmpty())
			throw new PostException("Invalid post id");

		return postOpt.get();
	}

	@Override
	public List<Comment> getAllComment(Integer postId) throws PostException, CommentException, LoginException {
		// TODO Auto-generated method stub

		checkLoginStatus();
		Post post = checkValidPostId(postId);

		List<Comment> commentList = post.getCommentList();
		if (commentList.isEmpty())
			throw new CommentException("No comments in this post");

		return commentList;
	}

	@Override
	public Comment getCommentById(Integer postId, Integer commentId)
			throws PostException, LoginException, CommentException {

		checkLoginStatus();
		Post post = checkValidPostId(postId);

		List<Comment> commentList = post.getCommentList();
		if (commentList.isEmpty())
			throw new CommentException("No comments in this post");

		Optional<Comment> commentOpt = commentRepo.findById(commentId);
		if (commentOpt.isEmpty())
			throw new CommentException("Comment not found with " + commentId);

		return commentOpt.get();
	}

	@Override
	public Comment createNewComment(Integer postId, CommentDTO comment)
			throws PostException, LoginException, CommentException {

		checkLoginStatus();
		Post post = checkValidPostId(postId);

		Comment newComment = new Comment();
		newComment.setPostId(postId);
		newComment.setDate(LocalDateTime.now());
		newComment.setText(comment.getText());

		List<Comment> commentList = post.getCommentList();
		commentList.add(newComment);
		post.setCommentList(commentList);

		return commentRepo.save(newComment);
	}

	@Override
	public Comment updateComment(Integer postId, CommentUpdateDTO comment, Integer commentId)
			throws PostException, LoginException, CommentException {

		checkLoginStatus();
		Post post = checkValidPostId(postId);

		List<Comment> commentList = post.getCommentList();
		if (commentList.isEmpty())
			throw new CommentException("No comments in this post");

		Optional<Comment> commentOpt = commentRepo.findById(commentId);
		if (commentOpt.isEmpty())
			throw new CommentException("Comment not found with " + commentId);

		Comment updatedComment = commentOpt.get();
		updatedComment.setId(commentId);
		updatedComment.setDate(LocalDateTime.now());
		updatedComment.setPostId(postId);
		updatedComment.setText(comment.getText());

		return commentRepo.save(updatedComment);
	}

	@Override
	public Comment deleteCommentById(Integer postId, Integer commentId)
			throws PostException, LoginException, CommentException {

		checkLoginStatus();
		Post post = checkValidPostId(postId);

		List<Comment> commentList = post.getCommentList();
		if (commentList.isEmpty())
			throw new CommentException("No comments in this post");

		Optional<Comment> commentOpt = commentRepo.findById(commentId);
		if (commentOpt.isEmpty())
			throw new CommentException("Comment not found with " + commentId);

		Comment deletedComment = commentOpt.get();
		commentRepo.delete(deletedComment);
		return deletedComment;

	}

}
