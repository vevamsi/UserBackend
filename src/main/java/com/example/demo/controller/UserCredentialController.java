package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PasswordDto;
import com.example.demo.model.UserAuthDetails;
import com.example.demo.service.UserCredentialService;
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://frontend-bucket-vamsi.s3-website-us-east-1.amazonaws.com")
@RestController
@RequestMapping("users/password")
public class UserCredentialController {

    @Autowired
    private UserCredentialService userCredService;

    @PostMapping("/save")
    public ResponseEntity<UserAuthDetails> saveUserPass(@RequestBody UserAuthDetails userPass) {
        UserAuthDetails savedUserPass = userCredService.saveUserPass(userPass);
        return ResponseEntity.ok(savedUserPass);
    }
    
    @PutMapping("/update-password")
    public ResponseEntity<String> updatePasswordByEmail(@RequestParam String email,@RequestParam String newPassword) {
        boolean updated = userCredService.updatePasswordByEmail(email, newPassword);
        if (updated) {
            return ResponseEntity.ok("Password updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        @PostMapping("/{userId}")
        public UserAuthDetails updatePassword(@PathVariable int userId, @RequestBody PasswordDto passwordDto) {
            
       	 UserAuthDetails user = userCredService.findByuserId(userId);
            if (user == null) {
                return null;
            }

            user.setPassword(passwordDto.getNewPassword());
          return  userCredService.saveUserPass(user);
        }
    

}

