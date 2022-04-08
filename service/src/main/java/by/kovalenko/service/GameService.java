package by.kovalenko.service;

import by.kovalenko.dto.GameResponse;
import org.springframework.security.core.Authentication;

public interface GameService {
    GameResponse crateGame(Authentication authentication);
}
