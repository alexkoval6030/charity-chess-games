package by.kovalenko.service.impl;

import by.kovalenko.dao.UserDao;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.UserNotFoundException;
import by.kovalenko.exception.ValidationException;
import by.kovalenko.service.UserService;
import by.kovalenko.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Pattern;

@Service("userService")
public class UserServiceImpl implements UserService {
    public static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private final int MIN_LENGTH_OF_NAME_FIELDS = 2;
    private final int MIN_LENGTH_USERNAME = 2;
    private final int MIN_LENGTH_PASSWORD = 7;

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserEntity createUser(UUID id, String firstname, String lastname, String email, String username, String password) throws ValidationException {
        UserEntity user = new UserEntity();
        user.setId(id);
        if (firstname != null && firstname.length() >= MIN_LENGTH_OF_NAME_FIELDS
                && lastname != null && lastname.length() >= MIN_LENGTH_OF_NAME_FIELDS){
            user.setFirstname(firstname);
            user.setLastname(lastname);
        } else {
            throw new ValidationException("First name and last name must be at least 2 characters");
        }
        if (email != null && EMAIL_VALIDATION_PATTERN.matcher(email).matches()){
            user.setEmail(email);
        } else {
            throw new ValidationException("Email is not correct");
        }
        if (username != null && username.length() >= MIN_LENGTH_USERNAME){
            user.setUsername(username);
        } else {
            throw new ValidationException("Username must be at least 2 characters");
        }
        if (password != null && password.length() >= MIN_LENGTH_PASSWORD){
            user.setPassword(password);
        } else {
            throw new ValidationException("Password must be at least 7 characters");
        }
        user.setRole(UserRole.USER);
        userDao.save(user);
        return user;
    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        UserEntity byUsernameAndPassword = userDao.findByUsernameAndPassword(username, password);
        UserEntity user = new UserEntity();
        if (byUsernameAndPassword != null){
            user.setId(byUsernameAndPassword.getId());
            user.setFirstname(byUsernameAndPassword.getFirstname());
            user.setLastname(byUsernameAndPassword.getLastname());
            user.setEmail(byUsernameAndPassword.getEmail());
            user.setUsername(byUsernameAndPassword.getUsername());
            user.setRole(byUsernameAndPassword.getRole());
        } else {
            try {
                throw new UserNotFoundException();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                throw new UserNotFoundException();
            }
        }
        return user;
    }
}
