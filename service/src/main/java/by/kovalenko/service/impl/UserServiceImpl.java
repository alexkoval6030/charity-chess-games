package by.kovalenko.service.impl;

import by.kovalenko.dto.UserRequest;
import by.kovalenko.dto.UserResponse;
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
    public UserResponse createUser(UserRequest userRequestFromController) throws ValidationException {
        UserRequest validateUserRequest = validateUserRequest(userRequestFromController);
        UserEntity userEntityFromService = userMapper.userRequestToUserEntity(validateUserRequest);
        userEntityFromService.setRole(UserRole.USER);
        UserEntity save = userRepository.save(userEntityFromService);
        return userMapper.userEntityToUserResponse(save);
    }

    private UserRequest validateUserRequest(UserRequest userRequestFromController) throws ValidationException {
        validateMinLength("First name", userRequestFromController.getFirstname(), MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("Last name", userRequestFromController.getLastname(), MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("Username", userRequestFromController.getUsername(), MIN_LENGTH_USERNAME);
        userRequestFromController.setPassword(validatePassword("Password", userRequestFromController.getPassword(), MIN_LENGTH_PASSWORD));
        validateMatches(userRequestFromController.getEmail());
        return userRequestFromController;
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
    public UserResponse findByUsername(String username) {
        UserEntity byUsername = userRepository.findByUsername(username);
        return userMapper.userEntityToUserResponse(byUsername);
    }

    @Override
    public UserResponse findByUsernameAndPassword(UserRequest userDto) {
        UserEntity userEntity = userMapper.userRequestToUserEntity(userDto);
        UserEntity byUsernameEqualsAndPasswordEquals = userRepository.findByUsernameEqualsAndPasswordEquals(userEntity.getUsername(), userEntity.getPassword());
        return userMapper.userEntityToUserResponse(byUsernameEqualsAndPasswordEquals);
    }
}
