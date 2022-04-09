package by.kovalenko.repositories;

import by.kovalenko.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);
}
