package com.example.demo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.dto.AddressDto;
import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    private final Logger logger = LoggerFactory.getLogger(AddressService.class);

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public AddressDto getAddressDtosByUserId(int userId) {
        Address address = addressRepository.findByUserId(userId);
        if (address != null) {
            AddressDto addressDto = new AddressDto(address.getCity(), address.getState(), address.getCountry(), address.getPincode());
            return addressDto;
        } else {
            logger.warn("Address not found for user ID: {}", userId);
            return null;
        }
    }

}