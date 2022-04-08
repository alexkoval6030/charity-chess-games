package by.kovalenko.repositories;

import by.kovalenko.entity.GameStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameStatusRepository extends JpaRepository<GameStatusEntity, UUID> {
}
