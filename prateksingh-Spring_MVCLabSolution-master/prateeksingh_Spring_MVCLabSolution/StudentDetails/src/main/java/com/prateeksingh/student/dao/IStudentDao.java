package com.prateeksingh.student.dao;

import java.util.List;

import com.prateeksingh.student.entity.Student;


public interface IStudentDao {

	List<Student> getStudents();

	void saveStudent(Student theStudent);

	Student getStudent(long theId);

	void deleteStudent(long id);
	
}
