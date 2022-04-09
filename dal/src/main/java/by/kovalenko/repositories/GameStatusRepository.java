package by.kovalenko.repositories;

import by.kovalenko.entity.GameStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameStatusRepository extends JpaRepository<GameStatusEntity, UUID> {
}
