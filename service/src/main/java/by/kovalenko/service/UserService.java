package by.kovalenko.service;

import by.kovalenko.dto.UserRequest;
import by.kovalenko.dto.UserResponse;
import by.kovalenko.exception.ValidationException;

public interface UserService {
    UserResponse createUser(UserRequest userDto) throws ValidationException;

    UserResponse findByUsername(String username);

    UserResponse findByUsernameAndPassword(UserRequest userDto);
}
