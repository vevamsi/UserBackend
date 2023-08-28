package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserAuthDetails;
import com.example.demo.repository.UserCredentialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserCredentialService {
	 @Autowired
	 private UserCredentialRepository userCredRepository;
	 private final Logger logger = LoggerFactory.getLogger(UserCredentialService.class);

	    public UserAuthDetails saveUserPass(UserAuthDetails userPass) {
	        return userCredRepository.save(userPass);
	    }

		public boolean updatePasswordByEmail(String email, String newPassword) {
			UserAuthDetails userPass = userCredRepository.findByUserEmail(email);
		    if (userPass != null) {
		        userPass.setPassword(newPassword);
		        userCredRepository.save(userPass);
		        return true;
		    }
			return false;
		}

		public UserAuthDetails findByuserId(int userId) {
	        UserAuthDetails userAuthDetails = userCredRepository.findByuserId(userId);
	        if (userAuthDetails != null) {
	            return userAuthDetails;
	        } else {
	            logger.warn("User credentials not found for user ID: {}", userId);
	            throw new UserNotFoundException("User credentials not found");
	        }
	    }

}
