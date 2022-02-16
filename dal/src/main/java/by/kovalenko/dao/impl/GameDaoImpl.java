package by.kovalenko.dao.impl;

import by.kovalenko.dao.GameDao;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.util.SessionUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.UUID;

public class GameDaoImpl implements GameDao {

    @Override
    public void save(GameEntity game) {
        Session session = SessionUtil.openSession();
        if (findById(game.getId()) != game) {
            session.save(game);
        } else {
            update(game);
        }
        session.close();
    }

    @Override
    public GameEntity findById(UUID id) {
        Session session = SessionUtil.openSession();
        GameEntity singleResult = session.createQuery("select * from GameEntity where id = " + id, GameEntity.class).getSingleResult();
        session.close();
        return singleResult;
    }

    @Override
    public List<GameEntity> findAll() {
        Session session = SessionUtil.openSession();
        List resultList = session.createQuery("from GameEntity where id != null ").getResultList();
        session.close();
        return resultList;
    }

    @Override
    public GameEntity findByGameStatusId(UUID id) {
        Session session = SessionUtil.openSession();
        GameEntity singleResult = session.createQuery("select * from GameEntity where gameStatus_id = " + id, GameEntity.class).getSingleResult();
        session.close();
        return singleResult;
    }

    @Override
    public List<GameEntity> findGameWithResult() {
        Session session = SessionUtil.openSession();
        List resultList = session.createQuery("from GameEntity where isCreatorWin != null ").getResultList();
        session.close();
        return resultList;
    }

    @Override
    public void update(GameEntity game) {
        Session session = SessionUtil.openSession();
        session.update(findById(game.getId()));
        session.close();
    }

    @Override
    public void delete(GameEntity game) {
        Session session = SessionUtil.openSession();
        session.delete(findById(game.getId()));
        session.close();
    }
}
