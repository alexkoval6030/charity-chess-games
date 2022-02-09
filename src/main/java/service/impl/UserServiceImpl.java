package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import jakarta.xml.bind.ValidationException;
import service.UserService;

import java.util.UUID;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    private static UserService INSTANCE = new UserServiceImpl();
    private final int MIN_LENGTH_OF_NAME_FIELDS = 2;
    public static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private final int MIN_LENGTH_USERNAME = 2;
    private final int MIN_LENGTH_PASSWORD = 7;

    private UserServiceImpl(){}

    public static UserService getInstance(){
        return INSTANCE;
    }

    @Override
    public User createUser(UUID id, String firstname, String lastname, String email, String username, String password) throws ValidationException {
        UserDao instance = UserDaoImpl.getInstance();
        User user = new User();
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
        if (username != null && username.length() >= MIN_LENGTH_USERNAME
                && password != null && password.length() >= MIN_LENGTH_PASSWORD){
            user.setUsername(username);
            user.setPassword(password);
        } else {
            throw new ValidationException("Username and password must be at least 8 characters");
        }
        instance.save(user);
        return user;
    }
}
