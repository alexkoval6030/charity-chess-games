package by.kovalenko.service.impl;

import by.kovalenko.dao.UserDao;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.ValidationException;
import by.kovalenko.service.UserService;
import by.kovalenko.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@Transactional
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
    // UUID - service or dao layer
    public UserEntity createUser(UUID id, String firstname, String lastname, String email, String username, String password) throws ValidationException {

        validateMinLength("First name", firstname, MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("Last name", lastname, MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("User name", username, MIN_LENGTH_USERNAME);
        validateMinLength("Password", password, MIN_LENGTH_PASSWORD);

        validateMatches("Email", email, EMAIL_VALIDATION_PATTERN);

        UserEntity user = new UserEntity();
        user.setId(id);
        user.setRole(UserRole.USER);

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        userDao.save(user);

        return user;
    }

    /*@Override
    public UserEntity createUser(String firstname, String lastname, String email, String username, String password) throws ValidationException {

        validateMinLength("First name", firstname, MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("Last name", lastname, MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("User name", username, MIN_LENGTH_USERNAME);
        validateMinLength("Password", password, MIN_LENGTH_PASSWORD);

        validateMatches("Email", email, EMAIL_VALIDATION_PATTERN);

        UserEntity user = new UserEntity();
        user.setRole(UserRole.USER);

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return userDao.save(user);
        // DAO should generate ID
    }*/

    private void validateMatches(String parameterName, String parameter, Pattern emailValidationPattern) throws ValidationException {
        if (parameter == null || !emailValidationPattern.matcher(parameter).matches()) {
            throw new ValidationException(parameterName + " is not correct.");
        }

    }

    private void validateMinLength(String parameterName, String parameter, int minLength) throws ValidationException{
        if (parameter == null || parameter.length() < minLength){
            throw new ValidationException(parameterName + "must be at least " + minLength + "characters.");
        }
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
}
