package by.kovalenko.service;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.exception.InsufficientFundsException;
import by.kovalenko.util.GameStatusName;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public interface GameService {
    GameDto createGame(UUID id, WalletDto walletDto, double creationBet) throws InsufficientFundsException;

    GameDto addUserToGame(UserDto userDto, WalletDto walletDto, UUID id, double connectionBet) throws InsufficientFundsException;

    GameDto findByGameId(UUID id);

    List<GameDto> findAllCreatedGames(UserDto userDto);

    HashSet<GameDto> findAllAttachedGames(UserDto userDto);

    List<GameDto> findAllByGameStatusAndCreatorIsNot(GameStatusName gameStatusName, UserDto userDto);

    HashSet<UserDto> findAllParticipants(UUID id);

    List<GameEntity> findAllByStatusAndCreatedBefore(GameStatusName gameStatusName, LocalDateTime createdDateTime);
}
