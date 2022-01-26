package dao;

import entity.UserEntity;
import org.hibernate.Session;
import service.SessionUtil;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(UserEntity userEntity) {
        Session session = SessionUtil.openSession();
        session.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        Session session = SessionUtil.openSession();
        UserEntity singleResult = session.createQuery("select * from UserEntity where email = " + email, UserEntity.class)
                .getSingleResult();
        return singleResult;
    }
}
