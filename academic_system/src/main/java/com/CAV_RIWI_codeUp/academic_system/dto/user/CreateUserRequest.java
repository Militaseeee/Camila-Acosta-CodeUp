package com.CAV_RIWI_codeUp.academic_system.dto.user;

import com.CAV_RIWI_codeUp.academic_system.model.Role;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private Role role;
}
