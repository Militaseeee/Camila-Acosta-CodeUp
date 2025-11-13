package com.CAV_RIWI_codeUp.academic_system.repository.interfaces;

import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    boolean existsByEmail(String email);
    List<User> findAll();
    List<User> findByRole(Role role);
    void deleteById(Long id);
    boolean existsById(Long id);
}
