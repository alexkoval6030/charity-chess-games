package by.kovalenko.service;

import by.kovalenko.dto.UserDto;
import by.kovalenko.exception.ValidationException;

import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto) throws ValidationException;

    UserDto findById(UUID id);

    UserDto findByUsername(String username);

    UserDto findByUsernameAndPassword(UserDto userDto);
}
