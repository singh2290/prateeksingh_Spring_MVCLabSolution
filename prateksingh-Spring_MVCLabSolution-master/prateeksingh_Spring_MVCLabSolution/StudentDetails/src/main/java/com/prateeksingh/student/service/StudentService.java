package com.prateeksingh.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prateeksingh.student.dao.IStudentDao;
import com.prateeksingh.student.entity.Student;



@Service
public class StudentService implements IStudentService{
	
	
	@Autowired
	private IStudentDao studentDao;

	@Override
	public Student getStudent(long theId) {
		return studentDao.getStudent(theId);
	}



	@Override
	public void deleteStudent(long theId) {
		studentDao.deleteStudent(theId);
		
	}



	@Override
	public List<Student> getAllStudents() {
		return studentDao.getStudents();
	}



	@Override
	public void saveStudent(Student theStudent) {
		studentDao.saveStudent(theStudent);
		
	}



}
