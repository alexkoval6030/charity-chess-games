package by.kovalenko.dao;

import by.kovalenko.entity.UserEntity;

public interface UserDao {
    UserEntity save(UserEntity user);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);

    void update(UserEntity user);

    void delete(UserEntity user);
}
