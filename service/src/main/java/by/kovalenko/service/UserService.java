package by.kovalenko.service;

import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.ValidationException;

import java.util.UUID;

public interface UserService {
    UserEntity createUser(UUID id, String firstname, String lastname, String email, String username, String password) throws ValidationException;
}
