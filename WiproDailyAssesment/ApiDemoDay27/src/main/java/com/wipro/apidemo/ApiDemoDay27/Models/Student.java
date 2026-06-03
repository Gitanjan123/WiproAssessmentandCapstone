package com.wipro.apidemo.ApiDemoDay27.Models;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Student {

    @Id
    int id;
    String name;
    String email;

    // COMPOSITION
    @Embedded
    Address address;

    // ONE TO ONE
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    Passport passport;

    // ONE TO MANY
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "st_id")
    List<Course> courselist;

    // MANY TO MANY ← NEW!
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_course",
        joinColumns = 
            @JoinColumn(name = "student_id"),
        inverseJoinColumns = 
            @JoinColumn(name = "course_id")
    )
    List<Course> enrolledCourses;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { 
        this.address = address; 
    }

    public Passport getPassport() { return passport; }
    public void setPassport(Passport passport) { 
        this.passport = passport; 
    }

    public List<Course> getCourselist() { return courselist; }
    public void setCourselist(List<Course> courselist) { 
        this.courselist = courselist; 
    }

    // NEW getter setter!
    public List<Course> getEnrolledCourses() { 
        return enrolledCourses; 
    }
    public void setEnrolledCourses(List<Course> enrolledCourses) { 
        this.enrolledCourses = enrolledCourses; 
    }
}