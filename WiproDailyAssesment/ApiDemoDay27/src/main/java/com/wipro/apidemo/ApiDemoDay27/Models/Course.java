package com.wipro.apidemo.ApiDemoDay27.Models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int price;

    // Course knows about MANY students! ← NEW
    @ManyToMany(mappedBy = "enrolledCourses")
    List<Student> students;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { 
        this.students = students; 
    }
}