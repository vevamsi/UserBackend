package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.PasswordDto;
import com.example.demo.dto.ResetDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.model.UserAuthDetails;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserCredentialService;
import com.example.demo.service.UserService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserCredentialService userPassService;
    @Autowired
    private AddressService addressService;
    

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
    	try {
            User u = new User();
            u.setFirst_name(userRegistrationDto.getFirst_name());
            u.setLast_name(userRegistrationDto.getLast_name());
            u.setEmail(userRegistrationDto.getEmail());

      
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
        UserDto userProfile = userService.getUserProfile(userId);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.notFound().build();
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
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 @PostMapping("/profile/{userId}")
 public User updateProfile(@PathVariable int userId, @RequestBody UserDto userDto) {
     User user = userService.getUserById(userId);

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
    public int login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getEmail(), loginDto.getPassword());
    }
    @PutMapping("/password/{user_id}")
    public ResponseEntity<String> resetPassword(@PathVariable int user_id, @RequestBody ResetDto resetDto) {
        User user = userService.getUserById(user_id);

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

}


