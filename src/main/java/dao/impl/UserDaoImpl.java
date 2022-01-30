package dao.impl;

import dao.UserDao;
import entity.UserEntity;
import org.hibernate.Session;
import service.SessionUtil;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(UserEntity userEntity) {
        Session session = SessionUtil.openSession();
        if (findByEmail(userEntity.getEmail()) != userEntity) {
            session.save(userEntity);
        } else {
            update(userEntity);
        }
        session.close();
    }

    @Override
    public UserEntity findByEmail(String email) {
        Session session = SessionUtil.openSession();
        UserEntity singleResult = session.createQuery("select * from UserEntity where email = " + email, UserEntity.class)
                .getSingleResult();
        session.close();
        return singleResult;
    }

    @Override
    public void update(UserEntity user) {
        Session session = SessionUtil.openSession();
        session.update(findByEmail(user.getEmail()));
        session.close();
    }

    @Override
    public void delete(UserEntity user) {
        Session session = SessionUtil.openSession();
        session.delete(findByEmail(user.getEmail()));
        session.close();
    }
}
