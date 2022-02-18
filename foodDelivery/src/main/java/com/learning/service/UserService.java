package com.learning.service;

import com.learning.dto.User;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;

public interface UserService {

	User addUser(User user) throws AlreadyExistsException;

	String deleteUserByUsername(String username);

	User[] getAllUsers();

	User getUserById(long userId) throws IdNotFoundException;

	User updateUser(User user) throws IdNotFoundException, AlreadyExistsException;
	
	

}