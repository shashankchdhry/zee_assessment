package com.learning.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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

import com.learning.dto.Login;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.LoginService;
import com.learning.service.RegisterService;

@RestController
@RequestMapping
public class RegisterController {

	@Autowired
	RegisterService registerService;

	@Autowired
	LoginService loginService;

	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody Register register) throws AlreadyExistsException {

		Register result = registerService.addRegister(register);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);

	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody Login login) {
		String result = loginService.authenticate(login);
		if (result.equals("success")) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "Success");
			return ResponseEntity.status(200).body(map);
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "Fail");
			return ResponseEntity.status(403).body(map);
		}

	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		Register[] registers = registerService.getAllRegisters();
		if (registers.length == 0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "No Record Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.status(200).body(registers);
	}

	@GetMapping("/users/{registerId}")
	public ResponseEntity<?> getUserById(@PathVariable("registerId") int registerId)
			throws IdNotFoundException {
		Register register = registerService.getRegisterById(registerId);
		return ResponseEntity.status(200).body(register);
	}

	@PutMapping("/update/users")
	public ResponseEntity<?> updateUserById(@RequestBody Register register)
			throws IdNotFoundException, AlreadyExistsException {
		Register result = registerService.updateRegister(register);
		return ResponseEntity.status(200).body(result);
	}

	@DeleteMapping("/delete/{registerId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("registerId") int registerId)
			throws IdNotFoundException {
		String result = registerService.deleteRegisterById(registerId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", result);
		return ResponseEntity.status(201).body(map);

	}

}
