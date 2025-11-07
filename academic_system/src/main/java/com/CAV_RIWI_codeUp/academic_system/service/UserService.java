package com.CAV_RIWI_codeUp.academic_system.service;

import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import com.CAV_RIWI_codeUp.academic_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to login all rol
    public Optional<User> login(String email, String password) {

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }

        return Optional.empty();
    }

    // Method to create user (Only Admin)
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("The email address is already registered");
        }

        return userRepository.save(user);
    }

    // Method to update the profile (All users)
    public User updateProfile(Long id_user, String phone) {
        User existUser = userRepository.findById(id_user)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (phone != null && !phone.isBlank()) {
            existUser.setPhone(phone);
        }

        return userRepository.save(existUser);
    }

    // Method to change password (All users)
    public User updatePassword(Long id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    // Method all Users (Only ADMIN)
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    // Method to list by role (ADMIN can see all teachers and students)
    public  List<User> listUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    // Method to delete user (Only ADMIN)
    public void deleteUser(Long id_user) {
        userRepository.deleteById(id_user);
    }

}
