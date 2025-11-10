package com.CAV_RIWI_codeUp.academic_system.dto.enrollment;

import lombok.Data;

// DTO for registerEnrollment()
@Data
public class EnrollmentRequest {
    private Long id_user;   // Student ID to be enrolled
    private Long id_course; // ID of the course you are enrolling in
}