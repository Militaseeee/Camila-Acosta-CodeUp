package com.CAV_RIWI_codeUp.academic_system.controller;

import com.CAV_RIWI_codeUp.academic_system.dto.course.AssignTeacherRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.course.CourseResponse;
import com.CAV_RIWI_codeUp.academic_system.dto.course.CreateCourseRequest;
import com.CAV_RIWI_codeUp.academic_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Create a course
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CreateCourseRequest request) {
        return ResponseEntity.ok(courseService.createCourse(request));
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // Update a course
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody CreateCourseRequest request) {
        return ResponseEntity.ok(courseService.updateCourse(id, request));
    }

    // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }

    // Assign a teacher to a course (we use PATCH for partial updates)
    @PatchMapping("/{id_course}/assign-teacher")
    public ResponseEntity<CourseResponse> assignTeacher(
            @PathVariable Long id_course,
            @RequestBody AssignTeacherRequest request) {
        return ResponseEntity.ok(courseService.assignTeacher(id_course, request));
    }
}
