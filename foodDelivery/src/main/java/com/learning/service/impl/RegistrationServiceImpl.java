package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Login;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.LoginRepository;
import com.learning.repo.RegisterRepository;
import com.learning.service.LoginService;
import com.learning.service.RegisterService;

@Service // using this we get the singleton object
public class RegistrationServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepository registerRepository;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private LoginService loginService;

	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public Register addRegister(Register register) throws AlreadyExistsException {

		boolean status = registerRepository.existsByEmail(register.getEmail());
		if (status) {
			throw new AlreadyExistsException("EMAIL ALREADY EXISTS!");
		}

		Register register2 = registerRepository.save(register);
		if (register2 != null) {
			Login login = new Login(register.getEmail(), register.getPassword(), register2);
			if (loginRepository.existsByEmail(register.getEmail())) {
				throw new AlreadyExistsException("USER ALREADY EXISTS IN LOGIN");
			}
			String result = loginService.addLogin(login);
			if (result == "success") {
				return register2;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Register updateRegister(Register register)
			throws IdNotFoundException, AlreadyExistsException {

		if (!registerRepository.existsById(register.getRegisterId())) {
			throw new IdNotFoundException("Sorry user with " + register.getRegisterId() + " not found");
		}
		return registerRepository.save(register);
	}

	@Override
	public Register getRegisterById(int registerId) throws IdNotFoundException {
		Optional<Register> optional = registerRepository.findById(registerId);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("sorry " + registerId + " not found");
		} else {
			return optional.get();
		}
	}

	@Override
	public Register[] getAllRegisters() {
		List<Register> list = registerRepository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		Register[] array = new Register[list.size()];
		return list.toArray(array);
	}

	@Override
	public String deleteRegisterById(int registerId) throws IdNotFoundException {

		if (!registerRepository.existsById(registerId)) {
			throw new IdNotFoundException("sorry user with id " + registerId + " not found");
		}
		registerRepository.deleteById(registerId);
		return "User Successfully deleted";
	}
}