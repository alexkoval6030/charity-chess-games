package by.kovalenko.service.impl;

import by.kovalenko.dto.GameDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.mapper.GameMapper;
import by.kovalenko.mapper.GameStatusMapper;
import by.kovalenko.mapper.UserMapper;
import by.kovalenko.repositories.GameRepository;
import by.kovalenko.service.GameService;
import by.kovalenko.service.GameStatusService;
import by.kovalenko.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final GameStatusService gameStatusService;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository,
                           GameMapper gameMapper,
                           GameStatusService gameStatusService,
                           UserService userService)
    {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.gameStatusService = gameStatusService;
        this.userService = userService;
    }

    @Override
    public GameDto createGame(Authentication authentication) {
        UserEntity userEntity = userService.findByUsername(authentication.getName());
        GameStatusEntity gameStatusEntity = gameStatusService.createGameStatus();
        GameEntity gameEntity = new GameEntity(userEntity, gameStatusEntity);

        gameEntity = gameRepository.save(gameEntity);

        return gameMapper.gameEntityToGameDto(gameEntity);
    }
}
