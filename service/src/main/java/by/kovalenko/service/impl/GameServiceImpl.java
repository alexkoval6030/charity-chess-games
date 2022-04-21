package by.kovalenko.service.impl;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.InsufficientFundsException;
import by.kovalenko.mapper.GameMapper;
import by.kovalenko.mapper.UserMapper;
import by.kovalenko.repositories.GameRepository;
import by.kovalenko.repositories.UserRepository;
import by.kovalenko.service.*;
import by.kovalenko.util.GameStatusName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final GameStatusService gameStatusService;
    private final UserService userService;
    private final StakeService stakeService;
    private final WalletService walletService;
    private final GameMapper gameMapper;
    private final UserMapper userMapper;

    @Override
    public GameDto createGame(UUID id, WalletDto walletDto, double creationBet) throws InsufficientFundsException {
        if (walletDto.getAvailableMoney() >= creationBet) {
            GameStatusEntity gameStatusEntity = gameStatusService.createGameStatus();
            GameEntity gameEntity = new GameEntity(userRepository.findById(id).get(), gameStatusEntity);
            gameEntity = gameRepository.save(gameEntity);
            stakeService.makeStake(id, gameEntity, creationBet);
            walletService.updateWalletMadeBet(walletDto.getId(), creationBet);
            return gameMapper.gameEntityToGameDto(gameEntity);
        } else {
            throw new InsufficientFundsException("Insufficient funds in the account to create the game");
        }
    }

    @Override
    public GameDto addUserToGame(UserDto userDto, WalletDto walletDto, UUID id, double connectionBet) throws InsufficientFundsException {
        if (walletDto.getAvailableMoney() >= connectionBet) {
            Optional<GameEntity> game = gameRepository.findById(id);
            Set<UserEntity> users = game.get().getUsers();
            users.add(userRepository.findByUsername(userDto.getUsername()));
            stakeService.makeStake(userDto.getId(), game.get(), connectionBet);
            walletService.updateWalletMadeBet(walletDto.getId(), connectionBet);
            return gameMapper.gameEntityToGameDto(game.get());
        } else {
            throw new InsufficientFundsException("Insufficient funds in the account to join the game");
        }
    }

    @Override
    public GameDto findByGameId(UUID id) {
        GameEntity gameEntity = gameRepository.findById(id).get();
        gameEntity.getCreator();
        gameEntity.getUsers();
        return gameMapper.gameEntityToGameDto(gameEntity);
    }

    @Override
    public List<GameDto> findAllCreatedGames(UserDto userDto) {
        List<GameEntity> gameEntityList = gameRepository.findAllByCreatorUsername(userDto.getUsername());
        return gameMapper.listGameEntityToListGameDto(gameEntityList);
    }

    @Override
    public HashSet<GameDto> findAllAttachedGames(UserDto userDto) {
        HashSet<GameEntity> gameEntityHashSet = gameRepository.findAllByUsersUsername(userDto.getUsername());
        return gameMapper.hashSetGameEntityToHashSetGameDto(gameEntityHashSet);
    }

    @Override
    public List<GameDto> findAllByGameStatusAndCreatorIsNot(GameStatusName gameStatusName, UserDto userDto) {
        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
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
