package com.CAV_RIWI_codeUp.academic_system.service;

import com.CAV_RIWI_codeUp.academic_system.dto.enrollment.EnrollmentRequest; // <-- 1. IMPORT AGREGADO
import com.CAV_RIWI_codeUp.academic_system.dto.enrollment.EnrollmentResponse;
import com.CAV_RIWI_codeUp.academic_system.dto.enrollment.UpdateEnrollmentDateRequest;
import com.CAV_RIWI_codeUp.academic_system.exception.BadRequestException; // <-- 2. 's' CORREGIDA
import com.CAV_RIWI_codeUp.academic_system.exception.ResourceNotFoundException; // <-- 2. 's' CORREGIDA
import com.CAV_RIWI_codeUp.academic_system.mapper.EntityDtoMapper; // <-- 3. MAPPER IMPORTADO
import com.CAV_RIWI_codeUp.academic_system.model.Course;
import com.CAV_RIWI_codeUp.academic_system.model.Enrollment;
import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import com.CAV_RIWI_codeUp.academic_system.repository.CourseRepository;
import com.CAV_RIWI_codeUp.academic_system.repository.EnrollmentRepository;
import com.CAV_RIWI_codeUp.academic_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityDtoMapper mapper; // <-- 4. MAPPER INYECTADO

    // Only Admin
    public EnrollmentResponse registerEnrollment(EnrollmentRequest request) {
        User student = userRepository.findById(request.getId_user())
                .orElseThrow(() -> new ResourceNotFoundException("Student (User) not found"));

        // Only the STUDENTS can enrollment
        if (student.getRole() != Role.STUDENT) {
            throw new BadRequestException("User is not a STUDENT");
        }

        Course course = courseRepository.findById(request.getId_course())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now()); // Current date

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return entityToResponse(savedEnrollment);
    }

    public void cancelEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }

    public EnrollmentResponse updateEnrollmentDate(Long id, UpdateEnrollmentDateRequest request) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        enrollment.setEnrollmentDate(request.getEnrollmentDate());
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return entityToResponse(updatedEnrollment);
    }

    // All user
    public EnrollmentResponse getEnrollmentDetails(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        return entityToResponse(enrollment);
    }

    // Teacher and Student
    public EnrollmentResponse viewGrades(Long id) {
        // Future logic:
        // 1. Look up Enrollment (already does this)
        // 2. Get the associated 'Grade' (enrollment.getGrade())
        // 3. Return the grade in the DTO
        return getEnrollmentDetails(id);
    }

    // Mapping Methods (Helpers)

    // This is the ONLY mapping method left, and it uses the mapper
    private EnrollmentResponse entityToResponse(Enrollment enrollment) {
        EnrollmentResponse response = new EnrollmentResponse();
        response.setId_enrollment(enrollment.getId_enrollment());
        response.setEnrollmentDate(enrollment.getEnrollmentDate());

        // Using the centralized mapper!
        response.setStudent(mapper.userEntityToBasicResponse(enrollment.getUser()));
        response.setCourse(mapper.courseEntityToBasicResponse(enrollment.getCourse()));

        // Lógica para la nota (cuando exista Grade)
        /*
        if (enrollment.getGrade() != null) {
            response.setScore(enrollment.getGrade().getScore());
        }
        */

        return response;
    }
}