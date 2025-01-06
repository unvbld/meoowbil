package com.mobil.mobil.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobil.mobil.model.User;
import com.mobil.mobil.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Enkripsi password
        user.setRole(role);
        userRepository.save(user); // Simpan ke database
    }

    // Method untuk mencari pengguna berdasarkan username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method untuk memperbarui informasi pengguna
    public void updateUser(Long id, String newPassword, String newRole) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword)); // Update dan enkripsi password baru
        }
        if (newRole != null && !newRole.isEmpty()) {
            user.setRole(newRole); // Update role
        }
        userRepository.save(user); // Simpan perubahan ke database
    }
}
