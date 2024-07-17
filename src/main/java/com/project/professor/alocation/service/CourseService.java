package com.project.professor.alocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.alocation.entity.Course;
import com.project.professor.alocation.repository.CourseRepository;

@Service
public class CourseService {
	
	private final CourseRepository courseRepository;
	
	public CourseService(CourseRepository courseRepository)
	{
		super();
		this.courseRepository = courseRepository;
	}
	
	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}
	
	public List<Course> findAll(String name) {
		return name == null ? courseRepository.findAll() : courseRepository.findByNameContainingIgnoreCase(name);
	}
	
	public Course create(Course course) {
		return saveInternal(course);
	}
	
	public Course update(Course course) {
		return courseRepository.existsById(course.getId()) == true ? saveInternal(course) : null;
	}
	
	public void deleteById(Long id) {
		if(courseRepository.existsById(id)) 
			courseRepository.deleteById(id);	
	}
	
	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}
	
	//-----------------------------------------------------------------------------------------------------------------------//
	
	private Course saveInternal(Course course) {
		return courseRepository.save(course);
	}

}
