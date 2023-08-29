package com.example.demo;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import com.example.demo.dto.AddressDto;
//import com.example.demo.dto.UserDto;
//import com.example.demo.exception.UserNotFoundException;
//import com.example.demo.model.Address;
//import com.example.demo.model.User;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.service.UserService;
//
//class UserControllerTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetUserProfile_Success() {
//        int userId = 1;
//        User user = new User();
//        user.setUser_id(userId);
//        user.setFirst_name("John");
//        user.setLast_name("Doe");
//        user.setEmail("john@example.com");
//
//        Address address = new Address();
//        address.setCity("City");
//        address.setState("State");
//        address.setCountry("Country");
//        address.setPincode("12345");
//        user.setAddress(address);
//
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
//
//        UserDto userProfile = userService.getUserProfile(userId);
//
//        assertNotNull(userProfile);
//        assertEquals(userId, userProfile.getUser_id());
//        assertEquals("John", userProfile.getFirst_name());
//        assertEquals("Doe", userProfile.getLast_name());
//        assertEquals("john@example.com", userProfile.getEmail());
//
//        AddressDto expectedAddressDto = new AddressDto("City", "State", "Country", "12345");
//        assertNotNull(userProfile.getAddress());
//        assertEquals(expectedAddressDto, userProfile.getAddress());
//    }
//
//    @Test
//    void testGetUserProfile_UserNotFound() {
//        int userId = 1;
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> userService.getUserProfile(userId));
//    }
//
//    @Test
//    void testGetUserProfile_NullAddress() {
//        int userId = 1;
//        User user = new User();
//        user.setUser_id(userId);
//        user.setFirst_name("John");
//        user.setLast_name("Doe");
//        user.setEmail("john@example.com");
//
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
//
//        UserDto userProfile = userService.getUserProfile(userId);
//
//        assertNotNull(userProfile);
//        assertEquals(userId, userProfile.getUser_id());
//        assertEquals("John", userProfile.getFirst_name());
//        assertEquals("Doe", userProfile.getLast_name());
//        assertEquals("john@example.com", userProfile.getEmail());
//        assertNull(userProfile.getAddress());
//    }
//
//    // Add more test cases for other methods
//}


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
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
        userRegistrationDto.setLast_name("Cena");
        userRegistrationDto.setEmail("johncena@gmail.com");
        userRegistrationDto.setPassword("1234");
        userRegistrationDto.setCity("New York");
        userRegistrationDto.setState("NY");
        userRegistrationDto.setCountry("USA");
        userRegistrationDto.setPincode("12345");
        userRegistrationDto.setDepartmentCode("IT");
        userRegistrationDto.setRole("USER");

        ResponseEntity<?> response = userController.registerUser(userRegistrationDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



    @Test
    public void testGetAllUsers() {
        ResponseEntity<List<User>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserByEmail() {
        String email = "vempativamsi10@gmail.com"; // Provide a valid email

        ResponseEntity<User> response = userController.getUserByEmail(email);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

}