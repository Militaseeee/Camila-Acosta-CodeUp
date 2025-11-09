package com.CAV_RIWI_codeUp.academic_system.dto.course;

import lombok.Data;

@Data
public class CreateCourseRequest {
    private String name;
    private String credits;
}