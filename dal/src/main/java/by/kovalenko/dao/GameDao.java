package by.kovalenko.dao;

import by.kovalenko.entity.GameEntity;

import java.util.List;
import java.util.UUID;

public interface GameDao {
    void save(GameEntity game);

    GameEntity findById(UUID id);

    List<GameEntity> findAll();

    GameEntity findByGameStatusId(UUID id);

    List<GameEntity> findGameWithResult();

    void update(GameEntity game);

    void delete(GameEntity game);
}
