package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.controller.UserController;
import com.example.demo.dto.AddressDto;
import com.example.demo.model.User;

@SpringBootTest
public class UserBackendApplicationTests {

    @Autowired
    private UserController userController;

    @BeforeEach
    public void setup() {
        // Initialize any setup if needed before each test
    }

    @Test
    public void testRegisterUser() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setFirst_name("John");
        userRegistrationDto.setLast_name("Doe");
        userRegistrationDto.setEmail("john.doe@example.com");
        userRegistrationDto.setPassword("1234");
        userRegistrationDto.setCity("New York");
        userRegistrationDto.setState("NY");
        userRegistrationDto.setCountry("USA");
        userRegistrationDto.setPincode("12345");

        ResponseEntity<?> response = userController.registerUser(userRegistrationDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    public void testGetUserProfile() {
//        int userId = 33; // Provide a valid user ID
//
//        ResponseEntity<UserDto> response = userController.getUserProfile(userId);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//
//        UserDto userDto = response.getBody();
//        assertEquals(userId, userDto.getUser_id());
//
//        // Update assertions with correct user profile values
//        assertEquals("Updated", userDto.getFirst_name());
//        assertEquals("User", userDto.getLast_name());
//        assertEquals("updated.user@example.com", userDto.getEmail());
//
//        AddressDto addressDto = userDto.getAddress();
//        assertNotNull(addressDto);
//        assertEquals("Updated City", addressDto.getCity());
//        assertEquals("Updated State", addressDto.getState());
//        assertEquals("Updated Country", addressDto.getCountry());
//        assertEquals("54321", addressDto.getPincode());
//    }


    @Test
    public void testGetAllUsers() {
        ResponseEntity<List<User>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Add more assertions to verify the response body
    }

    @Test
    public void testGetUserByEmail() {
        String email = "vamsi@gmail.com"; // Provide a valid email

        ResponseEntity<User> response = userController.getUserByEmail(email);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Add more assertions to verify the response body
    }

//    @Test
//    public void testUpdateProfile() {
//        int userId = 33; // Provide a valid user ID
//        UserDto userDto = new UserDto();
//        userDto.setFirst_name("Updated");
//        userDto.setLast_name("User");
//        userDto.setEmail("updated.user@example.com");
//
//        AddressDto addressDto = new AddressDto();
//        addressDto.setCity("Updated City");
//        addressDto.setState("Updated State");
//        addressDto.setCountry("Updated Country");
//        addressDto.setPincode("54321");
//        userDto.setAddress(addressDto);
//
//        User updatedUser = userController.updateProfile(userId, userDto);
//        assertNotNull(updatedUser);
//        assertEquals("Updated", updatedUser.getFirst_name());
//        assertEquals("User", updatedUser.getLast_name());
//
//        AddressDto updatedAddressDto = userDto.getAddress();
//        assertNotNull(updatedAddressDto);
//        assertEquals("Updated City", updatedAddressDto.getCity());
//        assertEquals("Updated State", updatedAddressDto.getState());
//        assertEquals("Updated Country", updatedAddressDto.getCountry());
//        assertEquals("54321", updatedAddressDto.getPincode());
//    }
}
