package com.wipro.apidemo.ApiDemoDay27.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.apidemo.ApiDemoDay27.Models.Student;
import com.wipro.apidemo.ApiDemoDay27.Repository.StudentRepository;

@Service
public class StudentService {
	@Autowired 
	StudentRepository repo;
	public Student saveStudent(Student student)
	{
		return repo.save(student);
	}
	public List<Student>getAllStudents()
	{
		return repo.findAll();
	}
	public Student getStudentById(int id)
	{
		return repo.findById(id).orElse(null);
	}
	
}
