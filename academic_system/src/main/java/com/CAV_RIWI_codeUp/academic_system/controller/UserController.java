package com.CAV_RIWI_codeUp.academic_system.controller;

import com.CAV_RIWI_codeUp.academic_system.dto.user.UpdatePasswordRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.UpdateProfileRequest;
import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import com.CAV_RIWI_codeUp.academic_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userRequest) {
        Optional<User> user = userService.login(userRequest.getEmail(), userRequest.getPassword());
        return user.isPresent()
                ? ResponseEntity.ok(user.get())
                : ResponseEntity.status(401).body("Incorrect email or password");
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User newUser = userService.createUser(user);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id_user}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable Long id_user, @RequestBody UpdateProfileRequest request) {
        try {
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
        List<User> users = userService.listAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> listUsersByRole(@PathVariable Role role) {
        List<User> users = userService.listUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User successfully deleted");
    }
}
