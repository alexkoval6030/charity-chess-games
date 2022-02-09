package dao.impl;

import dao.UserDao;
import entity.User;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;
import service.SessionUtil;

public class UserDaoImpl implements UserDao {
    private static final UserDao INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(User user) {
        Session session = SessionUtil.openSession();
        session.save(user);
        session.close();
    }

    @Override
    public User findByEmail(String email) throws NoResultException {
        try {
            Session session = SessionUtil.openSession();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            JpaRoot<User> from = criteriaQuery.from(User.class);
            criteriaQuery.select(from).where(criteriaBuilder.equal(from.get("email"), email));
            User user = session.createQuery(criteriaQuery).getSingleResult();
            session.close();
            return user;
        } catch (NoResultException e){
            return null;
        }
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
