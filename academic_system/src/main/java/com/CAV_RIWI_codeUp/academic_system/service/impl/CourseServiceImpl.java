package com.CAV_RIWI_codeUp.academic_system.service.impl;

import com.CAV_RIWI_codeUp.academic_system.exception.BadRequestException;
import com.CAV_RIWI_codeUp.academic_system.exception.ResourceNotFoundException;
import com.CAV_RIWI_codeUp.academic_system.model.Course;
import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import com.CAV_RIWI_codeUp.academic_system.repository.interfaces.ICourseRepository;
import com.CAV_RIWI_codeUp.academic_system.repository.interfaces.IUserRepository; // Necesitamos buscar al profe
import com.CAV_RIWI_codeUp.academic_system.service.interfaces.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final ICourseRepository courseRepository;
    private final IUserRepository userRepository; // Inyectamos la interfaz de usuario

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course); // El teacher es null para crear
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long id, Course courseData) {
        Course existingCourse = getCourseById(id); // Reutilizamos el mtodo

        existingCourse.setName(courseData.getName());
        existingCourse.setCredits(courseData.getCredits());

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public Course assignTeacher(Long idCourse, Long idTeacher) {
        Course course = getCourseById(idCourse);

        User teacher = userRepository.findById(idTeacher)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + idTeacher));

        if (!teacher.getRole().equals(Role.TEACHER)) {
            throw new BadRequestException("User with id " + idTeacher + " is not a TEACHER");
        }

        course.setTeacher(teacher);
        return courseRepository.save(course);
    }
}
