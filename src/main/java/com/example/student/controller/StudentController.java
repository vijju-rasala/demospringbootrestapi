package com.example.student.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.student.model.Course;

@RestController
public class StudentController {


	@GetMapping("/students/{studentId}/courses")
	public List<String> retrieveCoursesForStudent(@PathVariable String studentId) {
		List<String> list = new ArrayList<>();
		list.add("Maths");
		list.add("Science");
		list.add("English");
		return list;
	}
	
	@GetMapping("/students/allcourses")
	public List<String> retrieveAllCoursesForStudent(@PathVariable String studentId) {
		Course course = null;
		
		int id = course.getId();
		
		List<String> list = new ArrayList<>();
		list.add("Maths");
		list.add("Science");
		list.add("English");
		return list;
	}
	
	@PostMapping("/students/{studentId}/courses")
	public ResponseEntity<Void> registerStudentForCourse(
			@PathVariable String studentId, @RequestBody Course newCourse) {

		Course course = newCourse;
		

		if (course == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(course.getId()).toUri();

//		ResponseEntity<String> response = ResponseEntity.created(location).build();
		
//		return ResponseEntity.ok("success");
		
		return ResponseEntity.created(location).build();
	}
}
