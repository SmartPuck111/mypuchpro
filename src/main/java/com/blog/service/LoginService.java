package com.blog.service;

import com.blog.exception.LoginException;
import com.blog.model.Login;

public interface LoginService {

	public String loginAccount(Login login) throws LoginException;

	public String logoutAccount(String email, String key) throws LoginException;

}
