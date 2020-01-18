package com.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> students;
	
	@PostConstruct
	public void loadDate() {
		students = new ArrayList<>();

		students.add(new Student("Vlad", "Hasporian"));
		students.add(new Student("Gaga", "Kiknadze"));
		students.add(new Student("Karim", "Mastan"));
	}
	
	@GetMapping("/students")
	public List<Student> showStudent(){
		
		return students;
		
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudentForId(@PathVariable int studentId) {
		if( (studentId >= students.size()) || (studentId < 0)) {
			throw new StudentNotFoundException("Student is not found " + studentId);
		}
		return students.get(studentId);
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorRespons> handlerException(StudentNotFoundException exc){
		
		StudentErrorRespons error = new StudentErrorRespons();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
}
