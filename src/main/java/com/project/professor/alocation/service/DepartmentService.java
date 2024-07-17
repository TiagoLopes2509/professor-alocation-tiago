package com.project.professor.alocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.alocation.entity.Department;
import com.project.professor.alocation.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository)
	{
		super();
		this.departmentRepository = departmentRepository;
	}
	
	public Department findById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}
	
	public List<Department> findAll(String name) {
		return name == null ? departmentRepository.findAll() : departmentRepository.findByNameContainingIgnoreCase(name);
	}
	
	public Department create(Department department) {
		return saveInternal(department);
	}
	
	public Department update(Department department) {
		return departmentRepository.existsById(department.getId()) == true ? saveInternal(department) : null;
	}
	
	public void deleteById(Long id) {
		if(departmentRepository.existsById(id)) 
			departmentRepository.deleteById(id);	
	}
	
	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}
	
	//-----------------------------------------------------------------------------------------------------------------------//
	
	private Department saveInternal(Department department) {
		return departmentRepository.save(department);
	}

}
