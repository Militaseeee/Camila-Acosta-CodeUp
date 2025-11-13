package com.CAV_RIWI_codeUp.academic_system.service;

import com.CAV_RIWI_codeUp.academic_system.dto.user.UpdateProfileRequest;
import com.CAV_RIWI_codeUp.academic_system.exception.BadRequestException;
import com.CAV_RIWI_codeUp.academic_system.exception.ResourceNotFoundException;
import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import com.CAV_RIWI_codeUp.academic_system.repository.interfaces.DataUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicexxxxxxxxxxxxxx {

    @Autowired
    private DataUserRepository userRepository;

    // Method to login all rol
//    public Optional<User> login(String email, String password) {
//
//        Optional<User> user = userRepository.findByEmail(email);
//        if (user.isPresent() && user.get().getPassword().equals(password)) {
//            return user;
//        }
//
//        return Optional.empty();
//    }

    // Se recibe un User es del dto -> solo user y pass
    public User login(User userType) {

        // Se busca en la BD el email del objeto que llego
        User userFromDb = userRepository.findByEmail(userType.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Comparamos la contra del objeto y de la BD
        if (!userFromDb.getPassword().equals(userType.getPassword())) {
            throw new BadRequestException("Incorrect password");
        }

        // 3. Se devuelve el usuario COMPLETO de la BD
        return userFromDb;
    }

    // Method to create user (Only Admin)
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("The email address is already registered");
        }

        return userRepository.save(user);
    }

    // Method to update the profile (All users)
//    public User updateProfile(Long id_user, String phone) {
//        User existUser = userRepository.findById(id_user)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        if (phone != null && !phone.isBlank()) {
//            existUser.setPhone(phone);
//        }
//
//        return userRepository.save(existUser);
//    }

    public User updateProfile(Long id, UpdateProfileRequest request) {
        User existUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Extraemos el dato del objeto aquí adentro
        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            existUser.setPhone(request.getPhone());
        }

        return userRepository.save(existUser);
    }

    // Method to change password (All users)
    public User updatePassword(Long id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
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
