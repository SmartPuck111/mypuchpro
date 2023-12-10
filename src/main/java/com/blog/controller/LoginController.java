package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.LoginException;
import com.blog.model.Login;
import com.blog.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<String> logIn(@RequestBody Login login) throws LoginException {
		return new ResponseEntity<String>(loginService.loginAccount(login), HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam("email") String email, @RequestParam("loginKey") String loginKey)
			throws LoginException {
		return new ResponseEntity<String>(loginService.logoutAccount(email, loginKey), HttpStatus.OK);
	}

}
