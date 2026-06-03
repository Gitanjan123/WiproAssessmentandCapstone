package com.wipro.Day16;

public class User {

    private String name;
    private String phone;
    private String image;
    private String designation;
    private String department;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { 
        this.designation = designation; 
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { 
        this.department = department; 
    }
}