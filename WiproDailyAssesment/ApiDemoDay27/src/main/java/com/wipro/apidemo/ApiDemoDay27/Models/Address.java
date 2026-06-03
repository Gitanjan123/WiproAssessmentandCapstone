package com.wipro.apidemo.ApiDemoDay27.Models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	String hno;
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	String city;
	
	
}
