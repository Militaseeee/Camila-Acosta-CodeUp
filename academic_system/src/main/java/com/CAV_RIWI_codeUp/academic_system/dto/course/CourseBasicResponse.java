package com.CAV_RIWI_codeUp.academic_system.dto.course;

import lombok.Data;

@Data
public class CourseBasicResponse {
    private Long id_course;
    private String name;
    private String credits;
}