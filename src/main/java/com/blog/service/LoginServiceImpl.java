package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.exception.LoginException;
import com.blog.model.Login;
import com.blog.model.User;
import com.blog.repository.LoginRepo;
import com.blog.repository.UserRepo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private LoginRepo loginRepo;

	@Override
	public String loginAccount(Login login) throws LoginException {

		User user = userRepo.findByEmail(login.getEmail());
		if (user == null)
			throw new LoginException("Invalid email");

		if (user.getPassword().equals(login.getPassword())) {

			Login loginData = loginRepo.findByEmail(login.getEmail());
			if (loginData != null)
				throw new LoginException("User already logged-In!");

			Login newLoginData = new Login();
			newLoginData.setEmail(login.getEmail());
			newLoginData.setPassword(login.getPassword());
			newLoginData.setDate(LocalDateTime.now());

			String key = RandomString.make(6);
			newLoginData.setLoginKey(key);
			loginRepo.save(newLoginData);

			return "Login Sucessufull! ( key - " + key + " )";

		} else {
			throw new LoginException("Invalid password");
		}
	}

	@Override
	public String logoutAccount(String email, String key) throws LoginException {

		User user = userRepo.findByEmail(email);
		if (user == null)
			throw new LoginException("Invalid email");

		Login loginData = loginRepo.findByEmail(email);
		if (loginData == null)
			throw new LoginException("User not logged-In!");

		if (loginData.getLoginKey().equals(key)) {

			Login deletedLoginData = loginData;
			loginRepo.delete(deletedLoginData);

			return "Logout succesfull!";

		} else {
			throw new LoginException("Invalid login-key");
		}
	}
}
