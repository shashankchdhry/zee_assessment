package com.learning.service;

import com.learning.dto.Login;

public interface LoginService {

	public String addLogin(Login login);

	public String authenticate(Login login);

}
