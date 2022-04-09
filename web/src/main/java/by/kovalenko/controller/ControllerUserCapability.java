package by.kovalenko.controller;

import by.kovalenko.dto.GameDto;
import by.kovalenko.mapper.UserMapper;
import by.kovalenko.service.GameService;
import by.kovalenko.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ControllerUserCapability {
    private final GameService gameService;
    private final UserService userService;
    private final UserMapper userMapper;

    public ControllerUserCapability(GameService gameService, UserService userService, UserMapper userMapper) {
        this.gameService = gameService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "/createGame")
    public String createGame(Authentication authentication, Model model){
        gameService.createGame(authentication);
        List<GameDto> allCreatedGames = gameService.findAllCreatedGames(authentication);
        model.addAttribute("allCreatedGames", allCreatedGames);
        return "createGame";
    }

    @GetMapping(path = "/myGames")
    public String myGame(Authentication authentication, Model model){
        List<GameDto> allCreatedGames = gameService.findAllCreatedGames(authentication);
        model.addAttribute("allCreatedGames", allCreatedGames);
        return "myGames";
    }
}