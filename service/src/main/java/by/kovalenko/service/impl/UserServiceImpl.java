package by.kovalenko.service.impl;

import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.ValidationException;
import by.kovalenko.mapper.GameMapper;
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
    private final GameMapper gameMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, GameMapper gameMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.gameMapper = gameMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) throws ValidationException {
        validateUserDto(userDto);
        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
        userEntity.setRole(UserRole.USER);
        return userMapper.userEntityToUserDto(userRepository.save(userEntity));
    }

    private void validateUserDto(UserDto userDto) throws ValidationException {
        validateMinLength("First name", userDto.getFirstname(), MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("Last name", userDto.getLastname(), MIN_LENGTH_OF_NAME_FIELDS);
        validateMinLength("Username", userDto.getUsername(), MIN_LENGTH_USERNAME);
        userDto.setPassword(validatePassword("Password", userDto.getPassword(), MIN_LENGTH_PASSWORD));
        validateMatches(userDto.getEmail());
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
    public UserDto findByUsernameAndPassword(UserDto userDto) {
        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
        UserEntity byUsernameEqualsAndPasswordEquals = userRepository.findByUsernameAndPassword(userEntity.getUsername(), userEntity.getPassword());
        return userMapper.userEntityToUserDto(byUsernameEqualsAndPasswordEquals);
    }
}
