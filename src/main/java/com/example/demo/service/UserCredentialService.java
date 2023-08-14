package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserAuthDetails;
import com.example.demo.repository.UserCredentialRepository;

@Service
public class UserCredentialService {
	 @Autowired
	 private UserCredentialRepository userCredRepository;

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
			// TODO Auto-generated method stub
			UserAuthDetails u =userCredRepository.findByuserId(userId);
			return u;
			
		}
}
