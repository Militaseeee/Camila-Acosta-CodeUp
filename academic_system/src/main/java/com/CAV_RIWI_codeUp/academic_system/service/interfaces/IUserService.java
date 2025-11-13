package com.CAV_RIWI_codeUp.academic_system.service.interfaces;

import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;

import java.util.List;

public interface IUserService {
    User login(User user);
    User createUser(User user);
    User updateProfile(Long id, String phone);
    User updatePassword(Long id, String newPassword);
    List<User> listAllUsers();
    List<User> listUsersByRole(Role role);
    void deleteUser(Long id);
}
