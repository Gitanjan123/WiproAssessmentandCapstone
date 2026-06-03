package com.wipro.apidemo.ApiDemoDay27.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Girl")
public class Girl extends User {

    private String danceTeam;   // ✅ small d

    public String getDanceTeam() { 
        return danceTeam;       // ✅ small d
    }
    
    public void setDanceTeam(String danceTeam) { 
        this.danceTeam = danceTeam;  // ✅ small d
    }
}