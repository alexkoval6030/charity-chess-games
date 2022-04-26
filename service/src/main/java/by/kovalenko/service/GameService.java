package by.kovalenko.service;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.exception.InsufficientFundsException;
import by.kovalenko.util.GameStatusName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public interface GameService {
    GameDto createGame(UUID id, WalletDto walletDto, double creationBet) throws InsufficientFundsException;

    GameDto addUserToGame(UserDto userDto, WalletDto walletDto, UUID id, double connectionBet) throws InsufficientFundsException;

    GameDto findByGameId(UUID id);

    Page<GameDto> findAllCreatedGames(UserDto userDto, Pageable pageable);

    Page<GameDto> findAllAttachedGames(UserDto userDto, Pageable pageable);

    Page<GameDto> findAllByGameStatusAndCreatorIsNot(GameStatusName gameStatusName, UserDto userDto, Pageable pageable);

    HashSet<UserDto> findAllParticipants(UUID id);

    List<GameEntity> findAllByStatusAndCreatedBefore(GameStatusName gameStatusName, Date createdDateTime);
}
