package com.cg.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.departmentservice.entity.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	 	
	@Query(value = "SELECT * FROM departments d WHERE d.department_code = :departmentCode", nativeQuery = true)
    Department findByDepartmentCode(@Param("departmentCode") String departmentCode);
}
