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

import com.blog.exception.LoginException;
import com.blog.exception.PostException;
import com.blog.exception.UserException;
import com.blog.model.Post;
import com.blog.model.PostDTO;
import com.blog.model.PostUpdateDTO;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPost() throws PostException, LoginException {
		return new ResponseEntity<List<Post>>(postService.getAllPost(), HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable("postId") Integer postId)
			throws PostException, LoginException {
		return new ResponseEntity<Post>(postService.getPostById(postId), HttpStatus.OK);
	}

	@PostMapping("/posts")
	public ResponseEntity<Post> createNewPost(@RequestBody PostDTO post)
			throws PostException, LoginException, UserException {
		return new ResponseEntity<Post>(postService.createNewPost(post), HttpStatus.CREATED);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<Post> updatePostById(@PathVariable("postId") Integer postId, @RequestBody PostUpdateDTO post)
			throws PostException, LoginException, UserException {
		return new ResponseEntity<Post>(postService.updatePost(post, postId), HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<Post> deletePostById(Integer postId) throws PostException, LoginException {
		return new ResponseEntity<Post>(postService.deletePostById(postId), HttpStatus.OK);
	}

}
