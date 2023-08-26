package com.cg.departmentservice.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cg.departmentservice.entity.Department;
import com.cg.departmentservice.service.DepartmentService;
//@CrossOrigin(origins = "http://frontend-bucket-vamsi.s3-website-us-east-1.amazonaws.com")
@RestController
@RequestMapping("api/departments")
public class DepartmentController {
	
    private DepartmentService departmentService;
    @Autowired
	 public DepartmentController(DepartmentService departmentService) {
	        this.departmentService = departmentService;
	    }
    // Build save department REST API
    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    // Build get department rest api
    @GetMapping("{department-code}")
    public ResponseEntity<Department> getDepartment(@PathVariable("department-code") String departmentCode){
        Department department = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    @GetMapping
    public List<Department> getAllDepartment()
    {
    	return departmentService.getAll();
    }
    
//    @DeleteMapping("{department-code}")
//    public ResponseEntity<String> delete(@PathVariable("department-code") String departmentCode) {
//        try {
//        	 Department department = departmentService.delete(departmentCode);
//            return ResponseEntity.ok("All users deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting users");
//        }
//    }
}
    

