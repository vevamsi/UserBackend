package com.example.demo.dto;

public class APIResponseDto {
	
	public UserDto user;
	public AddressDto address;
	public DepartmentDto department;
	
	public APIResponseDto(UserDto user, AddressDto address, DepartmentDto department) {
		this.user = user;
		this.address = address;
		this.department = department;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public DepartmentDto getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDto department) {
		this.department = department;
	}
	
}
