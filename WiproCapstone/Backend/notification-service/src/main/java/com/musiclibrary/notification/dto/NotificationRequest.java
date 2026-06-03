package com.musiclibrary.notification.dto;

import lombok.Data;

@Data
public class NotificationRequest {
	private String subject;
	private String message;
	private String type;
	private String recipientEmail;
}
