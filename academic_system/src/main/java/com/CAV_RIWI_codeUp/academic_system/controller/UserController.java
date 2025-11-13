package com.CAV_RIWI_codeUp.academic_system.controller;

import com.CAV_RIWI_codeUp.academic_system.dto.user.CreateUserRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.LoginRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.UpdatePasswordRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.UpdateProfileRequest;
import com.CAV_RIWI_codeUp.academic_system.mapper.UserMapper;
import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import com.CAV_RIWI_codeUp.academic_system.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        try {
            // Mapper: LoginRequest -> User (con email y pass)
            User userCandidate = UserMapper.INSTANCE.toUser(request);

            // Service: Recibe objeto User
            User loggedUser = userService.login(userCandidate);

            return ResponseEntity.ok(loggedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            // Mapper: CreateUserRequest -> User
            User userToCreate = UserMapper.INSTANCE.toUser(request);

            // Service: Recibe objeto User
            User newUser = userService.createUser(userToCreate);

            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id_user}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable Long id_user, @RequestBody UpdateProfileRequest request) {
        try {
            // Como en la Interfaz definimos (Long, String acá extraemos el teléfono
            User updatedUser = userService.updateProfile(
                    id_user,
                    request.getPhone()
            );
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordRequest request) {
        try {

            User updatedUser = userService.updatePassword(id, request.getNewPassword());
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> listUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.listUsersByRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User successfully deleted");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
