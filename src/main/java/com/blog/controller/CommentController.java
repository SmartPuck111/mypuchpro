package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.CommentException;
import com.blog.exception.LoginException;
import com.blog.exception.PostException;
import com.blog.model.Comment;
import com.blog.model.CommentDTO;
import com.blog.model.CommentUpdateDTO;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<Comment>> getAllComment(@PathVariable("postId") Integer postId)
			throws PostException, CommentException, LoginException {
		return new ResponseEntity<List<Comment>>(commentService.getAllComment(postId), HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Comment> getCommentById(@PathVariable("postId") Integer postId,
			@PathVariable("commentId") Integer commentId) throws PostException, LoginException, CommentException {
		return new ResponseEntity<Comment>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<Comment> createNewComment(@RequestBody CommentDTO comment,
			@PathVariable("postId") Integer postId) throws PostException, CommentException, LoginException {
		return new ResponseEntity<Comment>(commentService.createNewComment(postId, comment), HttpStatus.CREATED);
	}

	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Comment> updateCommentById(@PathVariable("postId") Integer postId,
			@PathVariable("commentId") Integer commentId, @RequestBody CommentUpdateDTO comment)
			throws PostException, LoginException, CommentException {
		return new ResponseEntity<Comment>(commentService.updateComment(postId, comment, commentId), HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Comment> deleteCommentById(@PathVariable("postId") Integer postId, @PathVariable("commentId") Integer commentId)
			throws PostException, LoginException, CommentException {
		return new ResponseEntity<Comment>(commentService.deleteCommentById(postId, commentId), HttpStatus.OK);
	}

}
