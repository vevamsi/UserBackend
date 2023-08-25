package com.cg.departmentservice.service;

import java.util.List;

import com.cg.departmentservice.entity.Department;

public interface DepartmentService {
    Department saveDepartment(Department department);

    Department getDepartmentByCode(String code);

	List<Department> getAll();
}
