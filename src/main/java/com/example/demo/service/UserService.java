package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.model.UserAuthDetails;
import com.example.demo.repository.UserCredentialRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email " + email + " not found.");
        }
        return user;
    }


    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	 public int login(String email, String password) {
	        User user = userRepository.findByEmail(email);

	        return user.getUser_id();
	 }

	public UserDto getUserProfile(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Address address = user.getAddress(); // Assuming you have a getter for address in User entity
            AddressDto addressDto = new AddressDto(address.getCity(), address.getState(), address.getCountry(), address.getPincode());
            
            return new UserDto(user.getUser_id(), user.getFirst_name(), user.getLast_name(), user.getEmail(), addressDto);
        }
        return null;

	}

	public void deleteAllUsers() {
		userRepository.deleteAll();
		
	}
	

	}



