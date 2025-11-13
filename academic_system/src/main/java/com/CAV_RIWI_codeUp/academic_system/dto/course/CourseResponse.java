package com.CAV_RIWI_codeUp.academic_system.dto.course;

// To return the complete course information, including the professor
import com.CAV_RIWI_codeUp.academic_system.dto.user.UserBasicResponse;
import lombok.Data;

@Data
public class CourseResponse {
    private Long id_course;
    private String name;
    private String credits;
    private UserBasicResponse teacher; // In this part, I use DTO
}