package by.kovalenko.dao.impl;

import by.kovalenko.dao.UserDao;
import by.kovalenko.entity.UserEntity;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import by.kovalenko.service.SessionUtil;

public class UserDaoImpl implements UserDao {
    private static final UserDao INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(UserEntity user) {
        Session session = SessionUtil.openSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public UserEntity findByEmail(String email) throws NoResultException {
        Session session = SessionUtil.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> from = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(from).where(criteriaBuilder.equal(from.get("email"), email));
        session.getTransaction().begin();
        UserEntity entity = session.createQuery(criteriaQuery).getResultStream().findFirst().orElse(null);
        session.getTransaction().commit();
        session.close();
        return entity;
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
        session.delete(user);
        session.close();
    }
}
