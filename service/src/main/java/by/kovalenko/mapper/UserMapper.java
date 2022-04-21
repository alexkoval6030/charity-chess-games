package by.kovalenko.mapper;

import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userEntityToUserDto(UserEntity userEntity);

    UserEntity userDtoToUserEntity(UserDto userDto);

    HashSet<UserDto> setUserEntityToSetUserDto(Set<UserEntity> userEntitySet);
}
