package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AddressDto;
import com.example.demo.model.Address;
import com.example.demo.service.AddressService;
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://frontend-bucket-vamsi.s3-website-us-east-1.amazonaws.com")
@RestController
@RequestMapping("/users/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/save")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        Address savedAddress = addressService.saveAddress(address);
        return ResponseEntity.ok(savedAddress);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<AddressDto> getUserAddresses(@PathVariable int userId) {
        AddressDto address = addressService.getAddressDtosByUserId(userId);
        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

