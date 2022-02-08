package service;

import entity.User;
import jakarta.xml.bind.ValidationException;

import java.util.UUID;

public interface UserService {
    User createUser(UUID id, String firstname, String lastname, String email, String username, String password) throws ValidationException;
}
