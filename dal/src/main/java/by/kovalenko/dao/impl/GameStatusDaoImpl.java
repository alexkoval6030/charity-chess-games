package by.kovalenko.dao.impl;

import by.kovalenko.dao.GameStatusDao;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.util.SessionUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class GameStatusDaoImpl implements GameStatusDao {
    @Override
    public void save(GameStatusEntity gameStatus) {
        Session session = SessionUtil.openSession();
        if (findById(gameStatus.getId()) != gameStatus) {
            session.save(gameStatus);
        } else {
            update(gameStatus);
        }
        session.close();
    }

    @Override
    public GameStatusEntity findById(UUID id) {
        Session session = SessionUtil.openSession();
        session.createQuery("select * from GameStatusEntity where id = " + id, GameStatusEntity.class).getSingleResult();
        session.close();
        return null;
    }

    @Override
    public List<GameStatusEntity> findByDate(LocalDate date) {
        Session session = SessionUtil.openSession();
        List resultList = session.createQuery("select * from GameStatusEntity where date = " + date).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public List<GameStatusEntity> findByStatus(String gameStatus) {
        Session session = SessionUtil.openSession();
        List resultList = session.createQuery("select * from GameStatusEntity where status = " + gameStatus).getResultList();
        session.close();
        return resultList;
    }

    @Override
    public void update(GameStatusEntity gameStatus) {
        Session session = SessionUtil.openSession();
        session.update(findById(gameStatus.getId()));
        session.close();
    }

    @Override
    public void delete(GameStatusEntity gameStatus) {
        Session session = SessionUtil.openSession();
        session.delete(findById(gameStatus.getId()));
        session.close();
    }
}
