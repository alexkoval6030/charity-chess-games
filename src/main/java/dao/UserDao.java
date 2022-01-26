package dao;

import entity.UserEntity;

public interface UserDao {
    void save(UserEntity user);

    UserEntity findByEmail(String email);
}
