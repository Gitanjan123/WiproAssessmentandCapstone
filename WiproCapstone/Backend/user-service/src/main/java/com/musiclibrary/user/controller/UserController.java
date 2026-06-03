package com.musiclibrary.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.musiclibrary.user.dto.UpdateUserRequest;
import com.musiclibrary.user.service.UserService;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService uservice;  // removed final

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            uservice.getUserById(id));
    }

    @GetMapping("/api/admin/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(
            uservice.getAllUsers());
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(
            uservice.updateUser(id, request));
    }

    @DeleteMapping("/api/admin/users/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id) {
        uservice.deleteUser(id);
        return ResponseEntity.ok(
            Map.of("message", "User deleted successfully"));
    }
}