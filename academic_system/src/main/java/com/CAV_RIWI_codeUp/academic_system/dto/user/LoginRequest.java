package com.CAV_RIWI_codeUp.academic_system.dto.user;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}