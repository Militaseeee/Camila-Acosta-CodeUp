package com.CAV_RIWI_codeUp.academic_system.controller;

import com.CAV_RIWI_codeUp.academic_system.dto.enrollment.EnrollmentRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.enrollment.EnrollmentResponse;
import com.CAV_RIWI_codeUp.academic_system.dto.enrollment.UpdateEnrollmentDateRequest;
import com.CAV_RIWI_codeUp.academic_system.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Only ADMIN
    @PostMapping("/register")
    public ResponseEntity<EnrollmentResponse> registerEnrollment(@RequestBody EnrollmentRequest request) {
        return ResponseEntity.ok(enrollmentService.registerEnrollment(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelEnrollment(@PathVariable Long id) {
        enrollmentService.cancelEnrollment(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }

    // We used PATCH for a partial update
    @PatchMapping("/{id}/date")
    public ResponseEntity<EnrollmentResponse> updateEnrollmentDate(
            @PathVariable Long id,
            @RequestBody UpdateEnrollmentDateRequest request) {
        return ResponseEntity.ok(enrollmentService.updateEnrollmentDate(id, request));
    }

    // All USERS
    @GetMapping("/{id}/details")
    public ResponseEntity<EnrollmentResponse> getEnrollmentDetails(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentDetails(id));
    }

    // Teacher and Student
    @GetMapping("/{id}/grades")
    public ResponseEntity<EnrollmentResponse> viewGrades(@PathVariable Long id) {
        // Por ahora, reutiliza la lógica de detalles
        // En el futuro, este endpoint podría tener lógica de seguridad
        // para verificar si el usuario es el Teacher del curso o el Student.
        return ResponseEntity.ok(enrollmentService.viewGrades(id));
    }
}