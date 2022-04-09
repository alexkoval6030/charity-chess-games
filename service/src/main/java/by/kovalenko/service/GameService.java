package by.kovalenko.service;

import by.kovalenko.dto.GameDto;
import org.springframework.security.core.Authentication;

public interface GameService {
    GameDto createGame(Authentication authentication);
}
