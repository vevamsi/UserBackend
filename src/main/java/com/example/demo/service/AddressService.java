package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.dto.AddressDto;
import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public AddressDto getAddressDtosByUserId(int userId) {
        Address address = addressRepository.findByUserId(userId);
        if (address != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(address.getCity());
            addressDto.setState(address.getState());
            addressDto.setCountry(address.getCountry());
            addressDto.setPincode(address.getPincode());
        return addressDto;
    }
        return null;
}
}