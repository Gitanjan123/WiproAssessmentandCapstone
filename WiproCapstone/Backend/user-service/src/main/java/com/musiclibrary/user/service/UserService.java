package com.musiclibrary.user.service;

import com.musiclibrary.user.dto.UpdateUserRequest;
import com.musiclibrary.user.dto.UserDTO;
import com.musiclibrary.user.entity.User;
import com.musiclibrary.user.exception.UserNotFoundException;
import com.musiclibrary.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Get user by ID
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                    new UserNotFoundException(
                        "User not found with id: " + id));
        return mapToDTO(user);
    }

    // Get all users
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Update user
    public UserDTO updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                    new UserNotFoundException(
                        "User not found with id: " + id));

        if (request.getFirstName() != null)
            user.setFirstName(request.getFirstName());
        if (request.getLastName() != null)
            user.setLastName(request.getLastName());
        if (request.getPhone() != null)
            user.setPhone(request.getPhone());

        if (request.getNewPassword() != null
                && !request.getNewPassword().isEmpty()) {
            if (!passwordEncoder.matches(
                    request.getCurrentPassword(),
                    user.getPassword())) {
                throw new RuntimeException(
                    "Current password is incorrect");
            }
            user.setPassword(
                passwordEncoder.encode(request.getNewPassword()));
        }

        return mapToDTO(userRepository.save(user));
    }

    // Delete user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(
                "User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Map entity to DTO
    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole().name());
        return dto;
    }
}