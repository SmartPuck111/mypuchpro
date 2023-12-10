package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.UserException;
import com.blog.model.User;
import com.blog.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public String createUser() throws UserException {
		// TODO Auto-generated method stub

		String name = "User";
		String email = "user@gmail.com";
		String password = "password";

		User existsAdmin = userRepo.findByEmail(email);
		if (existsAdmin != null)
			throw new UserException(
					"User already created, the credentials => Email : " + email + ", Password : " + password);

		User admin = new User();
		admin.setName(name);
		admin.setEmail(email);
		admin.setPassword(password);

		userRepo.save(admin);

		return "User created => Email : " + email + ", Password : " + password;
	}

}
