package com.wipro.DIDemo;

public class Address {
    private String city;

    // Add this constructor ← THIS IS MISSING
    public Address(String city) {
        this.city = city;
    }

    // Keep default constructor too
    public Address() {}

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void show() {
        System.out.println("City: " + city);
    }
}