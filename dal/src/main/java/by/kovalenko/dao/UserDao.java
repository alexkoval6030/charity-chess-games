package by.kovalenko.dao;

import by.kovalenko.entity.UserEntity;

public interface UserDao {
    void save(UserEntity user);

    UserEntity findByEmail(String email);

    UserEntity findByUsernameAndPassword(String username, String password);

    void update(UserEntity user);

    void delete(UserEntity user);
}
