package com.ms.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ms.user.model.UserModel;
import com.ms.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional //Permite realizar o rollback do método
	public UserModel save(UserModel userModel) {
		var userExists = userRepository.findByEmailContainingIgnoreCaseOrderByName(userModel.getEmail());
		if(userExists.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return userRepository.save(userModel);
	}
}