package com.musiclibrary.admin.dto;

import lombok.Data;

@Data
public class CreateAdminRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}