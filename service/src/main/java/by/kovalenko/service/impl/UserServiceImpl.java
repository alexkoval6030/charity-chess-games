package by.kovalenko.service.impl;

import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.ValidationException;
import by.kovalenko.mapper.UserMapper;
import by.kovalenko.repositories.UserRepository;
import by.kovalenko.service.UserService;
import by.kovalenko.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    public static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private final int MIN_LENGTH_OF_NAME_FIELDS = 2;
    private final int MIN_LENGTH_USERNAME = 2;
    private final int MIN_LENGTH_PASSWORD = 7;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity createUser(UserDto userDtoFromController) throws ValidationException {
        UserEntity userEntityFromService = userMapper.userDtoToUserEntity(validateUserDto(userDtoFromController));
        userEntityFromService.setRole(UserRole.USER);

        return userRepository.save(userEntityFromService);
    }

    private UserDto validateUserDto(UserDto userDtoFromController) throws ValidationException {
        validateMinLength("First name", userDtoFromController.getFirstname(), MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("Last name", userDtoFromController.getLastname(), MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("Username", userDtoFromController.getUsername(), MIN_LENGTH_USERNAME);
        userDtoFromController.setPassword(validatePassword("Password", userDtoFromController.getPassword(), MIN_LENGTH_PASSWORD));
        validateMatches(userDtoFromController.getEmail());
        return userDtoFromController;
    }

    private String validatePassword(String parameterName, String parameter, int minLength) throws ValidationException {
        if (parameter == null || parameter.length() < minLength) {
            throw new ValidationException(parameterName + " must be at least " + minLength + "characters.");
        }
        return passwordEncoder.encode(parameter);
    }

    private void validateMatches(String parameter) throws ValidationException {
        if (parameter == null || !UserServiceImpl.EMAIL_VALIDATION_PATTERN.matcher(parameter).matches()) {
            throw new ValidationException("Email" + " is not correct.");
        }

    }

    private void validateMinLength(String parameterName, String parameter, int minLength) throws ValidationException {
        if (parameter == null || parameter.length() < minLength) {
            throw new ValidationException(parameterName + " must be at least " + minLength + "characters.");
        }
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameEqualsAndPasswordEquals(username, password);
    }


}
