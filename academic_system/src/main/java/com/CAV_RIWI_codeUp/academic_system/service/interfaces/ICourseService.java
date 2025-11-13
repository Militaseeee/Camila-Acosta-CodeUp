package com.CAV_RIWI_codeUp.academic_system.service.interfaces;

import com.CAV_RIWI_codeUp.academic_system.dto.course.CourseResponse;
import com.CAV_RIWI_codeUp.academic_system.dto.course.CreateCourseRequest;
import com.CAV_RIWI_codeUp.academic_system.model.Course;
import java.util.List;

public interface ICourseService {

    Course createCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    Course updateCourse(Long id, Course courseData);
    void deleteCourse(Long id);

    // Asignar profe recibe ID del curso y ID del profe
    Course assignTeacher(Long idCourse, Long idTeacher);

}
