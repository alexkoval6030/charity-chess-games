package by.kovalenko.service;

import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.UserNotFoundException;
import by.kovalenko.exception.ValidationException;

import java.util.UUID;

public interface UserService {
    UserEntity createUser(UUID id, String firstname, String lastname, String email, String username, String password) throws ValidationException;

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);
}
