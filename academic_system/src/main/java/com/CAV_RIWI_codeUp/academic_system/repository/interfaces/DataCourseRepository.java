package com.CAV_RIWI_codeUp.academic_system.repository.interfaces;

import com.CAV_RIWI_codeUp.academic_system.model.Course;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DataCourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(User teacher);
}