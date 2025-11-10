package com.CAV_RIWI_codeUp.academic_system.repository;

import com.CAV_RIWI_codeUp.academic_system.model.Course;
import com.CAV_RIWI_codeUp.academic_system.model.Enrollment;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Method for searching all registrations for a specific course -> (CourseService will use it to list students)
    List<Enrollment> findByCourse(Course course);

    // Method for searching all registrations for a specific student
    List<Enrollment> findByUser(User user);
}