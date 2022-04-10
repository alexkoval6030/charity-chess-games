package by.kovalenko.service;

import by.kovalenko.dto.GameDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.util.GameStatusName;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public interface GameService {
    GameDto createGame(Authentication authentication);

    List<GameDto> findAllCreatedGames(Authentication authentication);

    HashSet<GameDto> findAllAttachedGames(Authentication authentication);

    List<GameDto> findAllByGameStatusAndCreatorIsNot(GameStatusName gameStatusName, Authentication authentication);

    List<GameEntity> findAllByStatusAndCreatedBefore(GameStatusName gameStatusName, LocalDateTime createdDateTime);
}
