package com.gl.studentmvc.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.studentmvc.entityclass.Student;
import com.gl.studentmvc.service.StudentServiceImpl;

@Controller
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;

	// add mapping for "/list"
	@RequestMapping("/")
	public String MainPage() {
		return "mainpage";
	}

	@RequestMapping("/list")
	public String listStudent(Model theModel) {

		System.out.println("request recieved");
		// get Books from db
		List<Student> theStudents = studentService.findAllStudentList();

		// add to the spring model
		theModel.addAttribute("Students", theStudents);

		return "studentdetail";
	}

	@RequestMapping("/Addform")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Student theStudent = new Student();

		theModel.addAttribute("Student", theStudent);

		return "addorupdateform";
	}

	@RequestMapping("/Updateform")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {

		// get the Book from the service
		Student theStudent = studentService.findById(theId);

		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);

		// send over to our form
		return "addorupdateform";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		} else
			theStudent = new Student(firstName, lastName, course, country);
		// save the Book
		studentService.save(theStudent);

		// use a redirect to prevent duplicate submissions
		return "redirect:/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {

		// delete the Book
		studentService.deleteById(theId);

		// redirect to /Books/list
		return "redirect:/list";

	}

}
