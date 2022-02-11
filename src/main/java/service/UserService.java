package service;

import entity.UserEntity;
import jakarta.xml.bind.ValidationException;

import java.util.UUID;

public interface UserService {
    UserEntity createUser(UUID id, String firstname, String lastname, String email, String username, String password) throws ValidationException;
}
