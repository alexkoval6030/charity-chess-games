package by.kovalenko.repositories;

import by.kovalenko.entity.StakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StakeRepository extends JpaRepository<StakeEntity, UUID> {
}
