package com.example.demo.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.DepartmentDto;

@FeignClient(value="DEPARTMENT-SERVICE",url = "http://localhost:8082/api/departments")
public interface APIClient {
	
	@GetMapping("/{department-code}")
	DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
	
	 @GetMapping
	 List<DepartmentDto> getAllDepartment();
	 
	 @PostMapping
	    DepartmentDto saveDepartment(@RequestBody DepartmentDto departmentDto);
	
}
