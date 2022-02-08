package dao;

import entity.User;

public interface UserDao {
    void save(User user);

    String findByEmail(String email);

    void update(User user);

    void delete(User user);
}
