package com.CAV_RIWI_codeUp.academic_system.mapper;

import com.CAV_RIWI_codeUp.academic_system.dto.course.CourseResponse;
import com.CAV_RIWI_codeUp.academic_system.dto.course.CreateCourseRequest;
import com.CAV_RIWI_codeUp.academic_system.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// Debo usa UserMapper para convertir el campo teacher
@Mapper(uses = {UserMapper.class})
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    // DTO -> Entidad
    Course toCourse(CreateCourseRequest request);

    // Entidad -> DTO
    CourseResponse toCourseResponse(Course course);
}
