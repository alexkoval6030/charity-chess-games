package by.kovalenko.service;

import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.ValidationException;

public interface UserService {
    UserDto createUser(UserDto userDto) throws ValidationException;

    UserEntity findByUsername(String username);

    UserDto findByUsernameAndPassword(UserDto userDto);
}
