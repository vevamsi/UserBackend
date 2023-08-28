package com.example.demo.dto;

public class DepartmentDto {

    private Long id;
    private String departmentName;
    private String departmentCode;
	public DepartmentDto(Long id, String departmentName, String departmentCode) {
		this.id = id;
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
    
}
