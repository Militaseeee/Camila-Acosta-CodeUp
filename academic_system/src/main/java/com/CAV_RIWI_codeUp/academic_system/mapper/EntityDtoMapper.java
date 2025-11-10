package com.CAV_RIWI_codeUp.academic_system.mapper;

import com.CAV_RIWI_codeUp.academic_system.dto.course.CourseBasicResponse;
import com.CAV_RIWI_codeUp.academic_system.dto.user.UserBasicResponse;
import com.CAV_RIWI_codeUp.academic_system.model.Course;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import org.springframework.stereotype.Component;

@Component // Lo convierte en un Bean de Spring
public class EntityDtoMapper {

    // Convierte la Entidad User a UserBasicResponse DTO (Lo usará CourseService y EnrollmentService)
    public UserBasicResponse userEntityToBasicResponse(User user) {
        UserBasicResponse response = new UserBasicResponse();
        response.setId_user(user.getId_user());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    // Convierte la Entidad Course a CourseBasicResponse DTO (Lo usará EnrollmentService)
    public CourseBasicResponse courseEntityToBasicResponse(Course course) {
        CourseBasicResponse response = new CourseBasicResponse();
        response.setId_course(course.getId_course());
        response.setName(course.getName());
        response.setCredits(course.getCredits());
        return response;
    }
}