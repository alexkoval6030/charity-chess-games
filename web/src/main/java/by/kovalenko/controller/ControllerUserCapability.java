package by.kovalenko.controller;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.mapper.UserMapper;
import by.kovalenko.service.GameService;
import by.kovalenko.service.UserService;
import by.kovalenko.util.GameStatusName;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    @GetMapping(path = "/viewAllCreatedGames")
    public String viewAllCreatedGames(Authentication authentication, Model model){
        List<GameDto> allCreatedGames = gameService.findAllCreatedGames(authentication);
        model.addAttribute("allCreatedGames", allCreatedGames);
        return "createGame";
    }

    @GetMapping(path = "/myGames")
    public String myGame(Authentication authentication, Model model){
        List<GameDto> allCreatedGames = gameService.findAllCreatedGames(authentication);
        model.addAttribute("allCreatedGames", allCreatedGames);
        HashSet<GameDto> allAttachedGames = gameService.findAllAttachedGames(authentication);
        model.addAttribute("allAttachedGames", allAttachedGames);
        return "myGames";
    }

    @GetMapping(path = "/listAvailableGames")
    public String listAvailableGames(Authentication authentication, Model model){
        List<GameDto> listAvailableGames = gameService.findAllByGameStatusAndCreatorIsNot(
                GameStatusName.PLANNED, authentication);
        model.addAttribute("listAvailableGames", listAvailableGames);
        return "listAvailableGames";
    }

    @GetMapping(path = "/viewAllAttachedGames")
    public String viewAllAttachedGames(Authentication authentication, Model model){
        Set<GameDto> allAttachedGames = gameService.findAllAttachedGames(authentication);
        model.addAttribute("allAttachedGames", allAttachedGames);
        return "attachedGames";
    }

    @GetMapping(path = "/viewParticipants")
    public String viewParticipants(@RequestParam("gameId") UUID id, Model model){
        GameDto gameDto = gameService.findByGameId(id);
        model.addAttribute("game", gameDto);
        return "/participantTable";
    }

    @GetMapping(path = "/joinGame/{gameId}")
    public String joinAvailableGames(Authentication authentication, @PathVariable("gameId") UUID id){
        gameService.addUserToGame(authentication, id);
        return "redirect:/listAvailableGames";
    }
}
