package by.kovalenko.mapper;

import by.kovalenko.dto.UserRequest;
import by.kovalenko.dto.UserResponse;
import by.kovalenko.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse userEntityToUserResponse(UserEntity userEntity);

    UserEntity userRequestToUserEntity(UserRequest userRequest);

    UserEntity userResponseToUserEntity(UserResponse userResponse);
}
