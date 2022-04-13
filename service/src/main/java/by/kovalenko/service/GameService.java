package by.kovalenko.service;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.util.GameStatusName;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public interface GameService {
    GameDto createGame(Authentication authentication);

    GameDto addUserToGame(Authentication authentication, UUID id);

    GameDto findByGameId(UUID id);

    List<GameDto> findAllCreatedGames(Authentication authentication);

    HashSet<GameDto> findAllAttachedGames(Authentication authentication);

    List<GameDto> findAllByGameStatusAndCreatorIsNot(GameStatusName gameStatusName, Authentication authentication);

    HashSet<UserDto> findAllParticipants(UUID id);

    List<GameEntity> findAllByStatusAndCreatedBefore(GameStatusName gameStatusName, LocalDateTime createdDateTime);
}
