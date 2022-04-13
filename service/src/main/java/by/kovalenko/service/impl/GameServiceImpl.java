package by.kovalenko.service.impl;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.mapper.GameMapper;
import by.kovalenko.mapper.UserMapper;
import by.kovalenko.repositories.GameRepository;
import by.kovalenko.repositories.UserRepository;
import by.kovalenko.service.GameService;
import by.kovalenko.service.GameStatusService;
import by.kovalenko.service.UserService;
import by.kovalenko.util.GameStatusName;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final GameMapper gameMapper;
    private final GameStatusService gameStatusService;
    private final UserService userService;
    private final UserMapper userMapper;

    public GameServiceImpl(GameRepository gameRepository,
                           UserRepository userRepository, GameMapper gameMapper,
                           GameStatusService gameStatusService,
                           UserService userService, UserMapper userMapper)
    {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.gameMapper = gameMapper;
        this.gameStatusService = gameStatusService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public GameDto createGame(Authentication authentication) {
        UserEntity userEntity = userService.findByUsername(authentication.getName());
        GameStatusEntity gameStatusEntity = gameStatusService.createGameStatus();
        GameEntity gameEntity = new GameEntity(userEntity, gameStatusEntity);

        gameEntity = gameRepository.save(gameEntity);

        return gameMapper.gameEntityToGameDto(gameEntity);
    }

    @Override
    public GameDto addUserToGame(Authentication authentication, UUID id) {
        Optional<GameEntity> game = gameRepository.findById(id);
        Set<UserEntity> users = game.get().getUsers();
        users.add(userRepository.findByUsername(authentication.getName()));
        return gameMapper.gameEntityToGameDto(game.get());
    }

    @Override
    public GameDto findByGameId(UUID id) {
        GameEntity gameEntity = gameRepository.findById(id).get();
        gameEntity.getCreator();
        gameEntity.getUsers();

        return gameMapper.gameEntityToGameDto(gameEntity);
    }

    @Override
    public List<GameDto> findAllCreatedGames(Authentication authentication) {
        List<GameEntity> gameEntityList = gameRepository.findAllByCreatorUsername(authentication.getName());
        return gameMapper.listGameEntityToListGameDto(gameEntityList);
    }

    @Override
    public HashSet<GameDto> findAllAttachedGames(Authentication authentication) {
        HashSet<GameEntity> gameEntityHashSet = gameRepository.findAllByUsersUsername(authentication.getName());
        return gameMapper.hashSetGameEntityToHashSetGameDto(gameEntityHashSet);
    }

    @Override
    public List<GameDto> findAllByGameStatusAndCreatorIsNot(GameStatusName gameStatusName, Authentication authentication) {
        UserEntity userEntity = userRepository.findByUsername(authentication.getName());
        List<GameEntity> gameEntityList = gameRepository.findAllByGameStatusGameStatusNameAndCreatorUsernameIsNotAndUsersIsNotContaining(gameStatusName, userEntity.getUsername(), userEntity);
        return gameMapper.listGameEntityToListGameDto(gameEntityList);
    }

    @Override
    public List<GameEntity> findAllByStatusAndCreatedBefore(GameStatusName gameStatusName, LocalDateTime createdDateTime) {
        return gameRepository.findAllByGameStatusGameStatusNameAndGameStatusDateBefore(gameStatusName, createdDateTime);
    }

    @Override
    public HashSet<UserDto> findAllParticipants(UUID id) {
        Optional<GameEntity> game = gameRepository.findById(id);
        Set<UserEntity> users = game.get().getUsers();
        return userMapper.setUserEntityToSetUserDto(users);
    }

}
