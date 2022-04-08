package by.kovalenko.controller;

import by.kovalenko.service.GameService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerUserCapability {
    private final GameService gameService;

    public ControllerUserCapability(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/createGame")
    public String createGame(Authentication authentication){
        gameService.crateGame(authentication);
        return "create_game";
    }
}
