package by.kovalenko.dao.impl;

import by.kovalenko.dao.UserDao;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.util.SessionUtil;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @Override
    public UserEntity save(UserEntity user) {
        Session session = SessionUtil.openSession();
        session.save(user);
        return user;
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
    public UserEntity findByUsername(String username) {
        Session session = SessionUtil.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> from = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(from).where(criteriaBuilder.equal(from.get("username"), username));
        UserEntity entity = session.createQuery(criteriaQuery).getResultStream().findFirst().orElse(null);
        return entity;
    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) {
        Session session = SessionUtil.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> from = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(from).where(criteriaBuilder.and(
                criteriaBuilder.equal(from.get("username"), username),
                criteriaBuilder.equal(from.get("password"), password)
        ));
        UserEntity entity = session.createQuery(criteriaQuery).getResultStream().findFirst().orElse(null);
        return entity;
    }

    @Override
    public void update(UserEntity user) {
        Session session = SessionUtil.openSession();
        session.update(findByEmail(user.getEmail()));
    }

    @Override
    public void delete(UserEntity user) {
        Session session = SessionUtil.openSession();
        session.delete(user);
    }
}
