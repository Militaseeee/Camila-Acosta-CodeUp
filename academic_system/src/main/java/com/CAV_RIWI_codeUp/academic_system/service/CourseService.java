package com.CAV_RIWI_codeUp.academic_system.service;

import com.CAV_RIWI_codeUp.academic_system.dto.course.AssignTeacherRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.course.CourseResponse;
import com.CAV_RIWI_codeUp.academic_system.dto.course.CreateCourseRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.UserBasicResponse;
import com.CAV_RIWI_codeUp.academic_system.exception.BadRequestException;
import com.CAV_RIWI_codeUp.academic_system.exception.ResourceNotFoundException;
import com.CAV_RIWI_codeUp.academic_system.model.Course;
import com.CAV_RIWI_codeUp.academic_system.model.Role;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import com.CAV_RIWI_codeUp.academic_system.repository.CourseRepository;
import com.CAV_RIWI_codeUp.academic_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository; // It is necessary to find the teacher

    // Method for creating a course
    public CourseResponse createCourse(CreateCourseRequest request) {
        Course course = new Course();
        course.setName(request.getName());
        course.setCredits(request.getCredits());
        // The teacher is assigned later, so 'teacher' is null when created

        Course savedCourse = courseRepository.save(course);
        return entityToResponse(savedCourse);
    }

    // Method to obtain a course by ID
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return entityToResponse(course);
    }

    // Method to obtain all courses
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    // Method for updating a course
    public CourseResponse updateCourse(Long id, CreateCourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        course.setName(request.getName());
        course.setCredits(request.getCredits());

        Course updatedCourse = courseRepository.save(course);
        return entityToResponse(updatedCourse);
    }

    // Method for deleting a course
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    // This section begins the Business Logic Methods (of UML)

    // Method for assigning a teacher to a course
    public CourseResponse assignTeacher(Long id_course, AssignTeacherRequest request) {
        Course course = courseRepository.findById(id_course)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id_course));

        User teacher = userRepository.findById(request.getId_teacher())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher (User) not found with id: " + request.getId_teacher()));

        // BUSINESS RULE -> We verify that the user is indeed a teacher
        if (!teacher.getRole().equals(Role.TEACHER)) {
            throw new BadRequestException("User with id " + request.getId_teacher() + " is not a TEACHER");
        }

        course.setTeacher(teacher);
        Course savedCourse = courseRepository.save(course);
        return entityToResponse(savedCourse);
    }

    // Mapping Methods (Helpers)
    // Convert the Course Entity to a CourseResponse DTO
    private CourseResponse entityToResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId_course(course.getId_course());
        response.setName(course.getName());
        response.setCredits(course.getCredits());

        // If the teacher is not null, map their basic data
        if (course.getTeacher() != null) {
            response.setTeacher(userEntityToBasicResponse(course.getTeacher()));
        }

        return response;
    }

    // Convert the User Entity to a UserBasicResponse DTO
    private UserBasicResponse userEntityToBasicResponse(User user) {
        UserBasicResponse response = new UserBasicResponse();
        response.setId_user(user.getId_user());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}
