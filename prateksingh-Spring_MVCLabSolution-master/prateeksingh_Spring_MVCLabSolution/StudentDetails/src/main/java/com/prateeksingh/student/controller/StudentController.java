package com.prateeksingh.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prateeksingh.student.entity.Student;
import com.prateeksingh.student.service.StudentService;

@Controller
@RequestMapping("/")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping()
	@Transactional
	public String loadHomePage(Model theModel) {
		return "redirect:/student/list";
	}
	
	@GetMapping("/student/list")
	@Transactional
	public String listCustomers(Model theModel) {
		List<Student> students = studentService.getAllStudents();
		theModel.addAttribute("students", students);
		return "list-students";
	}
	
	@GetMapping("/student/showForm")
	@Transactional
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "student-form";
	}
	
	@PostMapping("/student/save")
	@Transactional
	public String saveStudent(@ModelAttribute("customer") Student theStudent) {
		studentService.saveStudent(theStudent);	
		return "redirect:/student/list";
	}
	
	@GetMapping("/student/updateForm")
	@Transactional
	public String showFormForUpdate(@RequestParam("id") int theId,
									Model theModel) {
		Student theCustomer = studentService.getStudent(theId);	
		theModel.addAttribute("student", theCustomer);
		return "student-form";
	}
	
	@GetMapping("/student/delete")
	@Transactional
	public String deleteStudent(@RequestParam("id") int theId) {
		studentService.deleteStudent(theId);
		return "redirect:/student/list";
	}

}
