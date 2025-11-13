package com.CAV_RIWI_codeUp.academic_system.controller;

import com.CAV_RIWI_codeUp.academic_system.dto.course.AssignTeacherRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.course.CourseResponse;
import com.CAV_RIWI_codeUp.academic_system.dto.course.CreateCourseRequest;
import com.CAV_RIWI_codeUp.academic_system.mapper.CourseMapper;
import com.CAV_RIWI_codeUp.academic_system.model.Course;
import com.CAV_RIWI_codeUp.academic_system.service.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    // Create a course
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CreateCourseRequest request) {

        Course courseToCreate = CourseMapper.INSTANCE.toCourse(request); // DTO -> Entidad

        Course createdCourse = courseService.createCourse(courseToCreate); // Este es con el servicio

        return ResponseEntity.ok(CourseMapper.INSTANCE.toCourseResponse(createdCourse)); // Entidad -> DTO Response
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(CourseMapper.INSTANCE.toCourseResponse(course));
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();

        // Convertimos la lista de Entidades a lista de DTOs
        List<CourseResponse> response = courses.stream()
                .map(CourseMapper.INSTANCE::toCourseResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // Update a course
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody CreateCourseRequest request) {
        Course courseData = CourseMapper.INSTANCE.toCourse(request);
        Course updatedCourse = courseService.updateCourse(id, courseData);
        return ResponseEntity.ok(CourseMapper.INSTANCE.toCourseResponse(updatedCourse));
    }

    // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // Assign a teacher to a course (we use PATCH for partial updates)
    @PatchMapping("/{id_course}/assign-teacher")
    public ResponseEntity<CourseResponse> assignTeacher(
            @PathVariable Long id_course,
            @RequestBody AssignTeacherRequest request) {

        // Pasamos IDs sueltos porque es más limpio para este caso específico
        Course course = courseService.assignTeacher(id_course, request.getId_teacher());
        return ResponseEntity.ok(CourseMapper.INSTANCE.toCourseResponse(course));
    }
}
