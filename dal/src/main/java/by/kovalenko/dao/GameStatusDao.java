package by.kovalenko.dao;

import by.kovalenko.entity.GameStatusEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface GameStatusDao {
    void save(GameStatusEntity gameStatus);

    GameStatusEntity findById(UUID id);

    List<GameStatusEntity> findByDate(LocalDate date);

    List<GameStatusEntity> findByStatus(String gameStatus);

    void update(GameStatusEntity gameStatus);

    void delete(GameStatusEntity gameStatus);
}
