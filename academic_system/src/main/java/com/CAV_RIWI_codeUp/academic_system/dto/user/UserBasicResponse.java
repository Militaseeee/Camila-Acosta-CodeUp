package com.CAV_RIWI_codeUp.academic_system.dto.user;

import lombok.Data;

// It's a DTO to display only the teacher's basic information within the course, without exposing their password

@Data
public class UserBasicResponse {
    private Long id_user;
    private String name;
    private String email;
}