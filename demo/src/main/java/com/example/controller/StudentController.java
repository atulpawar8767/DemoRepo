package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
		Student student = studentService.getStudentById(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") int id) {
		studentService.deleteStudentById(id);
		return new ResponseEntity<String>("Student of id " + id + " deleted successfully", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/")
	public ResponseEntity<Student> addStudent(@RequestBody @Valid Student request){
		Student student = studentService.addStudent(request);
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}
	
	@GetMapping("/email")
	public ResponseEntity<Boolean> isStudentValid(@RequestParam(name = "email") String email) {
		boolean studentByEmail = studentService.getStudentByEmail(email);
		System.out.println("Hello");
		return new ResponseEntity<Boolean>(studentByEmail, HttpStatus.OK);
	}
	
	
	

}
