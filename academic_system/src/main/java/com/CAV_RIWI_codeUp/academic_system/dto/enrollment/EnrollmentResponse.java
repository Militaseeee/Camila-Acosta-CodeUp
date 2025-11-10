package com.CAV_RIWI_codeUp.academic_system.dto.enrollment;

import com.CAV_RIWI_codeUp.academic_system.dto.course.CourseBasicResponse;
import com.CAV_RIWI_codeUp.academic_system.dto.user.UserBasicResponse;
import lombok.Data;

import java.time.LocalDate;

// Response DTO for viewEnrollmentDetails()
@Data
public class EnrollmentResponse {
    private Long id_enrollment;
    private LocalDate enrollmentDate;
    private UserBasicResponse student;
    private CourseBasicResponse course;
    // private Double score; // <-- Lo añadirás aquí cuando crees Grade
}