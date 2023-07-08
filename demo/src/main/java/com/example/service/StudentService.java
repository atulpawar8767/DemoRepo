package com.example.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.exception.DuplicateUserException;
import com.example.repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;

	public Student getStudentById(int id) {
		return studentRepo.findById(id).get();
	}

	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	public void deleteStudentById(int id) {
		studentRepo.deleteById(id);
	}

	public Student addStudent(Student request) {
		Student student = null;
		try {
		  student= studentRepo.save(request);
		} catch (Exception e) {
			throw new DuplicateUserException("Email is already exist");
		}
		return student;
	}

	public boolean getStudentByEmail(String email) {
         Student student = studentRepo.findByEmail(email);
         if(Objects.isNull(student)) {
        	 return false;
         }
         return true;
	}
	
	
}
