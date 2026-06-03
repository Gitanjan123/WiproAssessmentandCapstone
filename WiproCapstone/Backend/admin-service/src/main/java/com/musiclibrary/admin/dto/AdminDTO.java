package com.musiclibrary.admin.dto;

import lombok.Data;

@Data
public class AdminDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String role;
}
