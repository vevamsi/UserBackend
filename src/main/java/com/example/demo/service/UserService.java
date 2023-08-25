package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.AddressDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private APIClient apiClient;

	@Autowired
	private ModelMapper mapper;

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	@CircuitBreaker(name = "USER-SERVICE")
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			return user;
		} else {
			logger.warn("User not found for email: {}", email);
			throw new UserNotFoundException("User with email " + email + " not found.");
		}
	}

	public User updateUser(User updatedUser) {
		return userRepository.save(updatedUser);
	}

	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User login(String email, String password) {
		User user = userRepository.findByEmail(email);
		int uid = userRepository.checkPassword(password);
		if (uid == user.getUser_id()) {
			return user;
		} else {
			logger.warn("User not found for email: {}", email);
			throw new UserNotFoundException("User with email " + email + " not found.");
		}
	}

	public UserDto getUserProfile(int userId) {
		User user = userRepository.findById(userId).orElse(null);
	//	UserDto userDto = mapper.map(user, UserDto.class);
		if (user != null) {
			Address address = user.getAddress();
			if (address != null) {
				AddressDto addressDto = new AddressDto(address.getCity(), address.getState(), address.getCountry(),
						address.getPincode());
				return new UserDto(user.getUser_id(), user.getFirst_name(), user.getLast_name(), user.getEmail(),
						addressDto,user.getDepartmentCode(),user.getRole());
			} else {
				logger.warn("Address not found for user ID: {}", userId);
			}
		} else {
			logger.warn("User not found for user ID: {}", userId);
		}
		return null;
	}

	public void deleteAllUsers() {
		userRepository.deleteAll();

	}

	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}

	public User updateUser(int userId, User user) {
		User u = userRepository.findById(userId).orElse(null);
		u.setFirst_name(user.getFirst_name());
		u.setLast_name(user.getLast_name());
		u.setEmail(user.getEmail());
		u.setAddress(user.getAddress());
		return u;
	}

	public APIResponseDto getUserById(int userId) {

		User user = userRepository.findById(userId).get();

		DepartmentDto departmentDto = apiClient.getDepartment(user.getDepartmentCode());

		UserDto userDto = mapper.map(user, UserDto.class);

		APIResponseDto apiResponseDto = new APIResponseDto(userDto, userDto.getAddress(), departmentDto);

		return apiResponseDto;
	}
	public List<User> getUsersByDepartmentCode(String departmentCode) {
        // Call the Feign Client to get department users by department code
     //   DepartmentDto department = apiClient.getDepartment(departmentCode);
		//UserDto userDto = mapper.map(User.class, UserDto.class);
        List<User> usersInDepartment = userRepository.findByDepartmentCode(departmentCode);
        return usersInDepartment;
    }

}
