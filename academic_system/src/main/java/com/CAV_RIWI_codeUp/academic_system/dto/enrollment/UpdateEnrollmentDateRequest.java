package com.CAV_RIWI_codeUp.academic_system.dto.enrollment;

import lombok.Data;
import java.time.LocalDate;

// For updateEnrollmentDate()
@Data
public class UpdateEnrollmentDateRequest {
    private LocalDate enrollmentDate;
}