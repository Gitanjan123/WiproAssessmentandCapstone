package com.musiclibrary.notification.dto;

import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {
    private Long id;
    private String subject;
    private String message;
    private String type;
    private String sentTo;
    private LocalDateTime sentAt;
    private String status;
}