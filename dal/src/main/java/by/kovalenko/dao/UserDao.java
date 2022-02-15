package by.kovalenko.dao;

import by.kovalenko.entity.UserEntity;

public interface UserDao {
    void save(UserEntity user);

    UserEntity findByEmail(String email);

    void update(UserEntity user);

    void delete(UserEntity user);
}
