package by.kovalenko.service;

import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.UserNotFoundException;
import by.kovalenko.exception.ValidationException;

import java.util.UUID;

public interface UserService {
    UserEntity createUser(UserDto userDto) throws ValidationException;

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);
}
