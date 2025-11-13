package com.CAV_RIWI_codeUp.academic_system.repository.interfaces;

import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DataUserRepository extends JpaRepository <User, Long> {

    // Search user by email (login)
    Optional<User> findByEmail(String email);

    // Check if an email address is already registered (to validate before creating)
    boolean existsByEmail(String email);

    // Get a list of users by role (Admin, Teacher, Student)
    List<User> findByRole(Role role);

}
