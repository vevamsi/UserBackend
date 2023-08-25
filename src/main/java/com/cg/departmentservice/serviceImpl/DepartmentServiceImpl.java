package com.cg.departmentservice.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.departmentservice.entity.Department;
import com.cg.departmentservice.repository.DepartmentRepository;
import com.cg.departmentservice.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
       return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentByCode(String departmentCode) {
    	return departmentRepository.findByDepartmentCode(departmentCode);
    }
    @Override
    public List<Department> getAll(){
    	return departmentRepository.findAll();
    }
  
}
