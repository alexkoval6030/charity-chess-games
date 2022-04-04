package by.kovalenko.repositories;

import by.kovalenko.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);

    UserEntity findByUsernameEqualsAndPasswordEquals(String username, String password);
}
