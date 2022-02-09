package dao;

import entity.User;

public interface UserDao {
    void save(User user);

    User findByEmail(String email);

    void update(User user);

    void delete(User user);
}
