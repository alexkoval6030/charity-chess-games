package by.kovalenko.repositories;

import by.kovalenko.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<GameEntity, UUID> {

    List<GameEntity> findAllByCreatorUsername(String username);
}
