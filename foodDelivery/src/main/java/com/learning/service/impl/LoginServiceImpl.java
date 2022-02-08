package com.learning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Login;
import com.learning.repo.LoginRepository;
import com.learning.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public String addLogin(Login login) {
		Login login2 = loginRepository.save(login);
		if (login2 != null) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public String authenticate(Login login) {
		if (loginRepository.existsByEmail(login.getEmail())) {
			return "success";
		}
		return "fail";
	}
}
