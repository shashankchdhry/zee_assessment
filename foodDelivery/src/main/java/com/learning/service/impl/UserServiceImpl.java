package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.User;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.UserRepository;
import com.learning.service.UserService;

@Service // using this we get the singleton object
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) throws IdNotFoundException, AlreadyExistsException {

		if (!userRepository.existsById(user.getUserId())) {
			throw new IdNotFoundException("Sorry user with " + user.getUserId() + " not found");
		}
		return userRepository.save(user);
	}

	@Override
	public User getUserById(long userId) throws IdNotFoundException {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("sorry " + userId + " not found");
		} else {
			return optional.get();
		}
	}

	@Override
	public User[] getAllUsers() {
		List<User> list = userRepository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		User[] array = new User[list.size()];
		return list.toArray(array);
	}

	@Override
	public String deleteUserByUsername(String username) {

		Optional<User> optional = userRepository.findByUsername(username);
		if (optional.isPresent()) {
			userRepository.deleteById(optional.get().getUserId());
			return "User Successfully deleted";
		}
		return "Not deleted";
	}
}