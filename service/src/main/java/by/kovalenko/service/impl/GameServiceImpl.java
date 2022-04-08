package by.kovalenko.service.impl;

import by.kovalenko.dto.GameRequest;
import by.kovalenko.dto.GameResponse;
import by.kovalenko.dto.GameStatusResponse;
import by.kovalenko.dto.UserResponse;
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
    private final GameStatusMapper gameStatusMapper;
    private final UserMapper userMapper;
    private final GameStatusService gameStatusService;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository,
                           GameMapper gameMapper,
                           GameStatusMapper gameStatusMapper,
                           UserMapper userMapper, GameStatusService gameStatusService,
                           UserService userService)
    {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.gameStatusMapper = gameStatusMapper;
        this.userMapper = userMapper;
        this.gameStatusService = gameStatusService;
        this.userService = userService;
    }

    @Override
    public GameResponse crateGame(Authentication authentication) {
        UserResponse byUsername = userService.findByUsername(authentication.getName());
        UserEntity userEntity = userMapper.userResponseToUserEntity(byUsername);
        GameStatusResponse gameStatusResponse = gameStatusService.createGameStatus();
        GameStatusEntity gameStatusEntity = gameStatusMapper.gameStatusResponseToGameStatusEntity(gameStatusResponse);
        GameRequest gameRequest = new GameRequest(userEntity, gameStatusEntity);
        GameEntity gameEntity = gameMapper.gameRequestToGameEntity(gameRequest);
        GameEntity save = gameRepository.saveAndFlush(gameEntity);
        GameResponse gameResponse = gameMapper.gameEntityToGameResponse(save);
        return gameResponse;
    }
}
