package com.musiclibrary.notification.repository;

import com.musiclibrary.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    // Use @Query instead of method name parsing
    @Query("SELECT n FROM Notification n ORDER BY n.sentAt DESC")
    List<Notification> findAllOrderBySentAtDesc();
}