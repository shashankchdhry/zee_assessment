package com.learning.service;

import com.learning.dto.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;

public interface RegisterService {

	Register addRegister(Register register) throws AlreadyExistsException;

	String deleteRegisterById(int registerId) throws IdNotFoundException;

	Register[] getAllRegisters();

	Register getRegisterById(int registerId) throws IdNotFoundException;

	Register updateRegister(Register register) throws IdNotFoundException, AlreadyExistsException;

}