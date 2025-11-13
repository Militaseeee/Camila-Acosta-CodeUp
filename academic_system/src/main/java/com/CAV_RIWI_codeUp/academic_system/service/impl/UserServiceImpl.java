package com.CAV_RIWI_codeUp.academic_system.service.impl;

import com.CAV_RIWI_codeUp.academic_system.exception.BadRequestException;
import com.CAV_RIWI_codeUp.academic_system.exception.ResourceNotFoundException;
import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import com.CAV_RIWI_codeUp.academic_system.repository.interfaces.IUserRepository;
import com.CAV_RIWI_codeUp.academic_system.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor; // <-- Lombok inyecta dependencias
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    // Inyectamos la interfaz del repositorio
    private final IUserRepository userRepository;

    @Override
    public User login(User userCandidate) {

        User userFromDb = userRepository.findByEmail(userCandidate.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!userFromDb.getPassword().equals(userCandidate.getPassword())) {
            throw new BadRequestException("Incorrect password");
        }
        return userFromDb;
    }

    @Override
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("The email address is already registered");
        }

        return userRepository.save(user);
    }

    @Override
    public User updateProfile(Long id, String phone) {

        User existUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (phone != null && !phone.isBlank()) {
            existUser.setPhone(phone);
        }
        return userRepository.save(existUser);
    }

    @Override
    public User updatePassword(Long id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> listUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
