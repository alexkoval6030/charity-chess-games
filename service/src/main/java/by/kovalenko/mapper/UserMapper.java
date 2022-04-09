package by.kovalenko.mapper;

import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userEntityToUserDto(UserEntity userEntity);

    UserEntity userDtoToUserEntity(UserDto userDto);
}
