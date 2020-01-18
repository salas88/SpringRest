package com.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	@GetMapping("/students")
	public List<Student> showStudent(){
		
		List<Student> students = new ArrayList<>();
		
		students.add(new Student("Vlad", "Hasporian"));
		students.add(new Student("Gaga", "Kiknadze"));
		students.add(new Student("Karim", "Mastan"));
		
		return students;
		
	}

}
