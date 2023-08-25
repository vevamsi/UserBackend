package com.example.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.AddressDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.ResetDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.model.UserAuthDetails;
import com.example.demo.service.APIClient;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserCredentialService;
import com.example.demo.service.UserService;
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://frontend-bucket-vamsi.s3-website-us-east-1.amazonaws.com")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	APIClient apiClient;
    @Autowired
    private UserService userService;
    @Autowired
    private UserCredentialService userPassService;
    @Autowired
    private AddressService addressService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
    	try {
            User u = new User();
            u.setFirst_name(userRegistrationDto.getFirst_name());
            u.setLast_name(userRegistrationDto.getLast_name());
            u.setEmail(userRegistrationDto.getEmail());
            u.setRole(userRegistrationDto.getRole());
            u.setDepartmentCode(userRegistrationDto.getDepartmentCode());
            User savedUser = userService.saveUser(u);
            
     
            UserAuthDetails userPass = new UserAuthDetails();
            userPass.setPassword(userRegistrationDto.getPassword());
            userPass.setUser(savedUser);

         
            userPassService.saveUserPass(userPass);

            Address address = new Address();
            address.setCity(userRegistrationDto.getCity());
            address.setState(userRegistrationDto.getState());
            address.setCountry(userRegistrationDto.getCountry());
            address.setPincode(userRegistrationDto.getPincode());
            address.setUser(savedUser);

  
            addressService.saveAddress(address);

            return ResponseEntity.ok().build(); // Return success response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return error response
        }
    }
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable int userId) {
        try {
            UserDto userProfile = userService.getUserProfile(userId);
            if (userProfile != null) {
                return ResponseEntity.ok(userProfile);
            } else {
                logger.warn("User profile not found for user ID: {}", userId);
                throw new UserNotFoundException("User profile not found");
            }
        } catch (Exception e) {
            logger.error("An error occurred while fetching user profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("Debug : " + users.size());
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
        
    }
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        try {
            User user = userService.getUserByEmail(email);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                logger.warn("User not found for email: {}", email);
                throw new UserNotFoundException("User not found");
            }
        } catch (Exception e) {
            logger.error("An error occurred while fetching user by email", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 @PostMapping("/profile/{userId}")
 public User updateProfile(@PathVariable int userId, @RequestBody UserDto userDto) {
     User user = userService.findById(userId);

     if (user == null) {
         return null; // Return appropriate response or handle the case
     }

     user.setFirst_name(userDto.getFirst_name());
     user.setLast_name(userDto.getLast_name());
     user.setEmail(userDto.getEmail());

     if (userDto.getAddress() != null) {
         if (user.getAddress() == null) {
             user.setAddress(new Address()); // Initialize if address doesn't exist
         }
         Address address = user.getAddress();
         AddressDto addressDto = userDto.getAddress();
         address.setCity(addressDto.getCity());
         address.setState(addressDto.getState());
         address.setCountry(addressDto.getCountry());
         address.setPincode(addressDto.getPincode());
     }

     return userService.updateUser(user);
 }
 @PostMapping("/update/{userId}")
 public User updateUser(@PathVariable int userId, @RequestBody APIResponseDto apiDto) {
     User user = userService.findById(userId);

     if (user == null) {
         return null; // Return appropriate response or handle the case
     }
     
     user.setFirst_name(apiDto.user.getFirst_name());
     user.setLast_name(apiDto.user.getLast_name());
     user.setEmail(apiDto.user.getEmail());

     if (apiDto.user.getAddress() != null) {
         if (user.getAddress() == null) {
             user.setAddress(new Address()); // Initialize if address doesn't exist
         }
         Address address = user.getAddress();
         AddressDto addressDto =apiDto.user.getAddress();
         address.setCity(addressDto.getCity());
         address.setState(addressDto.getState());
         address.setCountry(addressDto.getCountry());
         address.setPincode(addressDto.getPincode());
     }
     user.setDepartmentCode(apiDto.department.getDepartmentCode());

     return userService.updateUser(user);
 }
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
        User updated = userService.updateUser(updatedUser);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getEmail(), loginDto.getPassword());
    }
    @PutMapping("/reset/{user_id}")
    public ResponseEntity<?> resetPassword(@PathVariable int user_id, @RequestBody ResetDto resetDto) {
        User user = userService.findById(user_id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        UserAuthDetails userAuthDetails = userPassService.findByuserId(user_id);

        if (userAuthDetails == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User credentials not found");
        }

        userAuthDetails.setPassword(resetDto.getPassword());
        userPassService.saveUserPass(userAuthDetails);

        return ResponseEntity.ok("Password reset successful");
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllUsers() {
        try {
            userService.deleteAllUsers();
            return ResponseEntity.ok("All users deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting users");
        }
    }
    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        
        if (email == null) {
            return ResponseEntity.badRequest().body("Email parameter is missing in the request body.");
        }
        
        User user = userService.getUserByEmail(email);
        
        if (user != null) {
            return ResponseEntity.ok().body(Collections.singletonMap("user_id", user.getUser_id()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable int user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.ok("User deleted successfully");
    }
    @PostMapping("/saveDepartment")
    public DepartmentDto saveDepartment(@RequestBody DepartmentDto departmentDto) {
        return apiClient.saveDepartment(departmentDto);
    }
    @GetMapping("/getDepartment/{departmentCode}")
    public DepartmentDto getDepartment(@PathVariable String departmentCode) {
        return apiClient.getDepartment(departmentCode);
    }

    @GetMapping("/getAllDepartments")
    public List<DepartmentDto> getAllDepartments() {
        return apiClient.getAllDepartment();
    }
    @GetMapping("/departmentUsers/{departmentCode}")
    public List<User> getDepartmentUsers(@PathVariable String departmentCode) {
        List<User> departmentUsers = userService.getUsersByDepartmentCode(departmentCode);
        return departmentUsers;
    }
}


