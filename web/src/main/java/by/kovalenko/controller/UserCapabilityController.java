package by.kovalenko.controller;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.dto.WalletDto;
import by.kovalenko.exception.InsufficientFundsException;
import by.kovalenko.service.GameService;
import by.kovalenko.service.WalletService;
import by.kovalenko.util.GameStatusName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserCapabilityController {
    private final GameService gameService;
    private final WalletService walletService;

    @PostMapping(path = "/createGame")
    public String createGame(
            @SessionAttribute(name = "user") UserDto userDto,
            @SessionAttribute(name = "wallet") WalletDto walletDto,
            @RequestParam(name = "creationBet") double creationBet,
            Model model
    ) {
        try {
            gameService.createGame(userDto.getId(), walletDto, creationBet);
        } catch (InsufficientFundsException e) {
            model.addAttribute("error", e.getMessage());
            return "homePage";
        }
        List<GameDto> allCreatedGames = gameService.findAllCreatedGames(userDto);
        model.addAttribute("allCreatedGames", allCreatedGames);
        walletDto = walletService.findById(userDto.getWallet().getId());
        return "/createGame";
    }

    @GetMapping(path = "/addFunds")
    public String addFunds() {
        return "addFunds";
    }

    @PostMapping(path = "/addFunds")
    public String afterAccountReplenishment(@ModelAttribute
                                            @SessionAttribute(name = "wallet") WalletDto walletDto,
                                            @RequestParam(name = "depositAmount") double depositAmount
    ) {
        walletService.addFunds(walletDto.getId(), depositAmount);
        return "redirect:/homePage";
    }

    @GetMapping(path = "/viewAllCreatedGames")
    public String viewAllCreatedGames(
            @SessionAttribute(name = "user") UserDto userDto,
            Model model
    ) {
        List<GameDto> allCreatedGames = gameService.findAllCreatedGames(userDto);
        model.addAttribute("allCreatedGames", allCreatedGames);
        return "createGame";
    }

    @GetMapping(path = "/myGames")
    public String myGame(
            @SessionAttribute(name = "user") UserDto userDto,
            Model model
    ) {
        List<GameDto> allCreatedGames = gameService.findAllCreatedGames(userDto);
        model.addAttribute("allCreatedGames", allCreatedGames);
        HashSet<GameDto> allAttachedGames = gameService.findAllAttachedGames(userDto);
        model.addAttribute("allAttachedGames", allAttachedGames);
        return "myGames";
    }

    @GetMapping(path = "/listAvailableGames")
    public String listAvailableGames(
            @SessionAttribute(name = "user") UserDto userDto,
            Model model
    ) {
        List<GameDto> listAvailableGames = gameService.findAllByGameStatusAndCreatorIsNot(
                GameStatusName.PLANNED, userDto);
        model.addAttribute("listAvailableGames", listAvailableGames);
        return "listAvailableGames";
    }

    @GetMapping(path = "/viewAllAttachedGames")
    public String viewAllAttachedGames(
            @SessionAttribute(name = "user") UserDto userDto,
            Model model
    ) {
        Set<GameDto> allAttachedGames = gameService.findAllAttachedGames(userDto);
        model.addAttribute("allAttachedGames", allAttachedGames);
        return "attachedGames";
    }

    @GetMapping(path = "/viewParticipants")
    public String viewParticipants(
            @RequestParam("gameId") UUID id,
            Model model
    ) {
        GameDto gameDto = gameService.findByGameId(id);
        model.addAttribute("game", gameDto);
        return "/participantTable";
    }

    @GetMapping(path = "/joinGame/{gameId}")
    public String joinAvailableGames(
            @SessionAttribute(name = "user") UserDto userDto,
            @SessionAttribute(name = "wallet") WalletDto walletDto,
            @PathVariable(name = "gameId") UUID id,
            @RequestParam(name = "connectionBet") double connectionBet,
            Model model
    ) {
        try {
            gameService.addUserToGame(userDto, walletDto, id, connectionBet);
        } catch (InsufficientFundsException e) {
            model.addAttribute("error", e.getMessage());
            return "homePage";
        }
        return "redirect:/listAvailableGames";
    }
}
