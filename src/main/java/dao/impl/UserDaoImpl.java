package dao.impl;

import dao.UserDao;
import entity.User;
import org.hibernate.Session;
import service.SessionUtil;

public class UserDaoImpl implements UserDao {
    private static final UserDao INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(User userEntity) {
        Session session = SessionUtil.openSession();
        session.save(userEntity);
        session.close();
    }

    @Override
    public String findByEmail(String email) {
        Session session = SessionUtil.openSession();
        User singleResult = session.createQuery("select * from User where email = " + email, User.class)
                .getSingleResult();
        String resultEmail = singleResult.getEmail();
        session.close();
        return resultEmail;
    }

    @Override
    public void update(User user) {
        Session session = SessionUtil.openSession();
        session.update(findByEmail(user.getEmail()));
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = SessionUtil.openSession();
        session.delete(user);
        session.close();
    }
}
