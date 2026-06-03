package com.wipro.apidemo.ApiDemoDay27.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.apidemo.ApiDemoDay27.Models.Student;
import com.wipro.apidemo.ApiDemoDay27.Services.StudentService;

@RestController
@RequestMapping("/wipro")
public class StudentController {
	@Autowired
	StudentService service;
	
	@PostMapping("/student")
	public Student saveStudent(@RequestBody Student student)
	{
		return service.saveStudent(student);
	}
	@GetMapping("/students")
	public List<Student>getAllStudents()
	{
		return service.getAllStudents();
	}
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable int id)
	{
		return service.getStudentById(id);
	}
}
