package com.musiclibrary.admin.controller;

import com.musiclibrary.admin.dto.*;
import com.musiclibrary.admin.service.AdminService;
import org.springframework.beans.factory.annotation
        .Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Get all admins
    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        return ResponseEntity.ok(
            adminService.getAllAdmins());
    }

    // Get admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            adminService.getAdminById(id));
    }

    // Update admin
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(
            @PathVariable Long id,
            @RequestBody UpdateAdminRequest request) {
        return ResponseEntity.ok(
            adminService.updateAdmin(id, request));
    }

    // Delete admin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(
            @PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok(
            Map.of("message",
                "Admin deleted successfully"));
    }
}