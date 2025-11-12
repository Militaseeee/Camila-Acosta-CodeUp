package com.CAV_RIWI_codeUp.academic_system.controller;

import com.CAV_RIWI_codeUp.academic_system.dto.user.CreateUserRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.LoginRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.UpdatePasswordRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.UpdateProfileRequest;
import com.CAV_RIWI_codeUp.academic_system.mapper.UserMapper;
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

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        Optional<User> user = userService.login(
//                request.getEmail(),
//                request.getPassword()
//        );
//
//        return user.isPresent()
//                ? ResponseEntity.ok(user.get()) : ResponseEntity.status(401).body("Incorrect email or password");
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Este es el mapper de LoginRequest (DTO) -> User (Entidad incompleta)
        User userCandidate = UserMapper.INSTANCE.toUser(request);

        // Este es el service pasamos el objeto
        User loggedUser = userService.login(userCandidate);

        // Aca deevolvemos el usuario completo que nos dio la BD
        return ResponseEntity.ok(loggedUser);
    }

//    @PostMapping
//    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
//        try {
//            User userToCreate = new User();
//            userToCreate.setName(request.getName());
//            userToCreate.setEmail(request.getEmail());
//            userToCreate.setPassword(request.getPassword());
//            userToCreate.setPhone(request.getPhone());
//            userToCreate.setRole(request.getRole());
//
//            User newUser = userService.createUser(userToCreate);
//
//            return ResponseEntity.ok(newUser);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            User userToCreate = UserMapper.INSTANCE.toUser(request);

            User newUser = userService.createUser(userToCreate);

            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


//    @PutMapping("/{id_user}/profile")
//    public ResponseEntity<?> updateProfile(@PathVariable Long id_user, @RequestBody UpdateProfileRequest request) {
//        try {
//            User updatedUser = userService.updateProfile(
//                    id_user,
//                    request.getPhone()
//            );
//            return ResponseEntity.ok(updatedUser);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PutMapping("/{id_user}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable Long id_user, @RequestBody UpdateProfileRequest request) {
        try {
            User updatedUser = userService.updateProfile(id_user, request);
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
