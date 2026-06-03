package com.wipro.apidemo.ApiDemoDay27.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Boy")
public class Boy extends User {
	private String cricketTeam;

	public String getCricketTeam() {
		return cricketTeam;
	}

	public void setCricketTeam(String cricketTeam) {
		this.cricketTeam = cricketTeam;
	}
	
}
