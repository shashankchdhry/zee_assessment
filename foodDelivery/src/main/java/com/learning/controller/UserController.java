package com.learning.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.User;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.UserService;

@RestController
@RequestMapping("/api/test")
public class UserController {

	@Autowired
	UserService userService;

//	@PostMapping("/register")
//	@PreAuthorize("hasRole('USER') or hasRole('USER')")
//	public ResponseEntity<?> addUser(@Valid @RequestBody User user) throws AlreadyExistsException {
//
//		User result = userService.addUser(user);
//		System.out.println(result);
//		return ResponseEntity.status(201).body(result);
//
//	}
//
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> authenticate(@RequestBody Login login) {
//		String result = loginService.authenticate(login);
//		if (result.equals("success")) {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("message", "Success");
//			return ResponseEntity.status(200).body(map);
//		} else {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("message", "Fail");
//			return ResponseEntity.status(403).body(map);
//		}
//
//	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN') ")
	public ResponseEntity<?> getAllUsers() {
		User[] users = userService.getAllUsers();
		if (users.length == 0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "No Record Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.status(200).body(users);
	}

	@GetMapping("/users/{userId}")
	@PreAuthorize("hasRole('ADMIN') ")
	public ResponseEntity<?> getUserById(@PathVariable("userId") long userId) throws IdNotFoundException {
		User user = userService.getUserById(userId);
		return ResponseEntity.status(200).body(user);
	}

	@PutMapping("/update/users")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
	public ResponseEntity<?> updateUserById(@RequestBody User user) throws IdNotFoundException, AlreadyExistsException {
		User result = userService.updateUser(user);
		return ResponseEntity.status(200).body(result);
	}

	@DeleteMapping("/delete/{username}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
	public ResponseEntity<?> deleteUserByUsername(@PathVariable("username") String username) throws IdNotFoundException {
		String result = userService.deleteUserByUsername(username);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", result);
		return ResponseEntity.status(201).body(map);

	}

}
