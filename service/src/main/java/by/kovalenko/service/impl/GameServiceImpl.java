package by.kovalenko.service.impl;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.entity.GameStatusEntity;
import by.kovalenko.entity.StakeEntity;
import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.InsufficientFundsException;
import by.kovalenko.mapper.GameMapper;
import by.kovalenko.mapper.UserMapper;
import by.kovalenko.mapper.WalletMapper;
import by.kovalenko.repositories.GameRepository;
import by.kovalenko.repositories.UserRepository;
import by.kovalenko.service.GameService;
import by.kovalenko.service.GameStatusService;
import by.kovalenko.service.StakeService;
import by.kovalenko.service.WalletService;
import by.kovalenko.util.GameStatusName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final static double INITIAL_FUND_SIZE = 0.0;
    private final static double COEFFICIENT_CHARITY_FUND = 0.95;

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final GameStatusService gameStatusService;
    private final StakeService stakeService;
    private final WalletService walletService;
    private final GameMapper gameMapper;
    private final UserMapper userMapper;
    private final WalletMapper walletMapper;

    @Override
    public GameDto createGame(UUID id, WalletDto walletDto, double creationBet) throws InsufficientFundsException {
        if (walletDto.getAvailableMoney() >= creationBet) {
            GameStatusEntity gameStatusEntity = gameStatusService.createGameStatus();
            GameEntity gameEntity = new GameEntity(userRepository.findById(id).get(), gameStatusEntity);
            gameEntity = gameRepository.save(gameEntity);
            StakeEntity stakeEntity = stakeService.makeStake(id, gameEntity, creationBet);
            gameEntity.setCreatorStake(stakeEntity);
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
    public Page<GameDto> findAllCreatedGames(UserDto userDto, Pageable pageable) {
        Page<GameEntity> gameEntityList = gameRepository.findAllByCreatorUsername(userDto.getUsername(), pageable);
        return gameEntityList.map(gameMapper::gameEntityToGameDto);
    }

    @Override
    public Page<GameDto> findAllAttachedGames(UserDto userDto, Pageable pageable) {
        Page<GameEntity> gameEntityList = gameRepository.findAllByUsersUsername(userDto.getUsername(), pageable);
        return gameEntityList.map(gameMapper::gameEntityToGameDto);
    }

    @Override
    public Page<GameDto> findAllByGameStatusAndCreatorIsNot(
            GameStatusName gameStatusName,
            UserDto userDto,
            Pageable pageable
    ) {
        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);

        Page<GameEntity> gameEntityList = gameRepository.
                findAllByGameStatusGameStatusNameAndCreatorUsernameIsNotAndUsersIsNotContaining(
                        gameStatusName,
                        userEntity.getUsername(),
                        userEntity,
                        pageable);
        return gameEntityList.map(gameMapper::gameEntityToGameDto);
    }

    @Override
    public List<GameEntity> findAllByStatusAndCreatedBefore(GameStatusName gameStatusName, Date createdDateTime) {
        return gameRepository.findAllByGameStatusGameStatusNameAndGameStatusDateBefore(gameStatusName, createdDateTime);
    }


    private GameEntity gameStatusChange(UUID gameId, Boolean result) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        gameEntity.get().getGameStatus().setGameStatusName(GameStatusName.FINISHED);
        gameEntity.get().setIsCreatorWin(result);
        return gameEntity.get();
    }

    @Override
    public GameEntity processingResultCreatorWin(WalletDto walletDto, UUID gameId, Boolean result) {
        GameEntity gameEntity = gameStatusChange(gameId, result);
        Double fund = INITIAL_FUND_SIZE;
        Set<UserEntity> users = gameEntity.getUsers();
        for (UserEntity user : users) {
            Double renewedFund = walletService.chargeOffReserveAccount(user, gameEntity);
            fund = fund + renewedFund;
        }
        Double renewedFund = walletService.chargeOffReserveAccount(gameEntity.getCreator(), gameEntity);
        fund = fund + renewedFund;
        double charityFund = fund * COEFFICIENT_CHARITY_FUND;
        double feeFund = fund - charityFund;
        walletService.addCharityFund(walletDto.getId(), charityFund);
        walletService.addFeeFund(walletDto.getId(), feeFund);
        return gameEntity;
    }

    @Override
    public GameEntity processingResultCreatorLose(WalletDto walletDto, UUID gameId, Boolean result) {
        GameEntity gameEntity = gameStatusChange(gameId, result);
        Double fund = INITIAL_FUND_SIZE;
        Set<UserEntity> users = gameEntity.getUsers();
        for (UserEntity user : users) {
            Double renewedFund = walletService.formationOfFund(user, gameEntity);
            fund = fund + renewedFund;
        }
        Double renewedFund = walletService.chargeOffReserveAccount(gameEntity.getCreator(), gameEntity);
        fund = fund + renewedFund;
        walletService.fundAllocation(fund, gameEntity, walletDto.getId());
        return gameEntity;
    }

    @Override
    public HashSet<UserDto> findAllParticipants(UUID id) {
        Optional<GameEntity> game = gameRepository.findById(id);
        Set<UserEntity> users = game.get().getUsers();
        return userMapper.setUserEntityToSetUserDto(users);
    }
}
