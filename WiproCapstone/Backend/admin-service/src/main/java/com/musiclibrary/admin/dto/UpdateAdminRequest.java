package com.musiclibrary.admin.dto;

import lombok.Data;

@Data
public class UpdateAdminRequest {
	private String firstName;
	private String lastName;
	private String phone;
}
