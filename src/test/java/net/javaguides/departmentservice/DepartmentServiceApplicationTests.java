package net.javaguides.departmentservice;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.departmentservice.entity.Department;
import com.cg.departmentservice.repository.DepartmentRepository;
import com.cg.departmentservice.serviceImpl.DepartmentServiceImpl;

class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDepartment() {
        Department department = new Department();
        department.setDepartmentCode("DEP001");
        department.setDepartmentName("HR");

        when(departmentRepository.save(department)).thenReturn(department);

        Department savedDepartment = departmentService.saveDepartment(department);

        assertNotNull(savedDepartment);
        assertEquals("DEP001", savedDepartment.getDepartmentCode());
        assertEquals("HR", savedDepartment.getDepartmentName());
    }

    @Test
    void testGetDepartmentByCode() {
        String departmentCode = "DEP001";
        Department department = new Department();
        department.setDepartmentCode(departmentCode);
        department.setDepartmentName("HR");

        when(departmentRepository.findByDepartmentCode(departmentCode)).thenReturn(department);

        Department retrievedDepartment = departmentService.getDepartmentByCode(departmentCode);

        assertNotNull(retrievedDepartment);
        assertEquals("DEP001", retrievedDepartment.getDepartmentCode());
        assertEquals("HR", retrievedDepartment.getDepartmentName());
    }

    @Test
    void testGetAllDepartments() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(1L, "HR", "DEP001"));
        departments.add(new Department(2L, "Finance", "DEP002"));

        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> retrievedDepartments = departmentService.getAll();

        assertNotNull(retrievedDepartments);
        assertEquals(2, retrievedDepartments.size());
    }
}

//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class DepartmentServiceApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}
