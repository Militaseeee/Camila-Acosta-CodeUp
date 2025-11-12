package com.CAV_RIWI_codeUp.academic_system.mapper;

import com.CAV_RIWI_codeUp.academic_system.dto.user.CreateUserRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.LoginRequest;
import com.CAV_RIWI_codeUp.academic_system.dto.user.UserBasicResponse;
import com.CAV_RIWI_codeUp.academic_system.model.User;
import org.mapstruct.factory.Mappers;

public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Convierte el Request del Controller a una Entidad User
    User toUser(CreateUserRequest request);

    // MapStruct pasará email y password al objeto User automáticamente
    User toUser(LoginRequest request);

    // Convierte una Entidad User a un DTO de respuesta (básico, sin password)
    UserBasicResponse toUserBasicResponse(User user);

}
