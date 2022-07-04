package com.prateeksingh.student.service;

import java.util.List;

import com.prateeksingh.student.entity.Student;

public interface IStudentService {

	List<Student> getAllStudents();

	void saveStudent(Student theStudent);

	Student getStudent(long theId);

	void deleteStudent(long theId);
}
