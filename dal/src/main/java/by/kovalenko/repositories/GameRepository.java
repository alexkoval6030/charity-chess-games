package by.kovalenko.repositories;

import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.util.GameStatusName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<GameEntity, UUID> {

    Page<GameEntity> findAllByCreatorUsername(String username, Pageable pageable);

    Page<GameEntity> findAllByUsersUsername(String username, Pageable pageable);

    Page<GameEntity> findAllByGameStatusGameStatusNameAndCreatorUsernameIsNotAndUsersIsNotContaining(GameStatusName gameStatusName, String username, UserEntity userEntity, Pageable pageable);

    List<GameEntity> findAllByGameStatusGameStatusNameAndGameStatusDateBefore(GameStatusName gameStatusName, Date createdDateTime);
}
