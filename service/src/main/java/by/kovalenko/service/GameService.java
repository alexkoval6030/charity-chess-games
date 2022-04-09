package by.kovalenko.service;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface GameService {
    GameDto createGame(Authentication authentication);

    List<GameDto> findAllCreatedGames(Authentication authentication);
}
