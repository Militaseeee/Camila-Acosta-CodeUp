package com.CAV_RIWI_codeUp.academic_system.repository.interfaces;

import com.CAV_RIWI_codeUp.academic_system.model.Course;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    Course save(Course course);
    Optional<Course> findById(Long id);
    List<Course> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    List<Course> findByTeacher(User teacher);
}
