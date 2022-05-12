package by.kovalenko.controller;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.UserDto;
import by.kovalenko.dto.WalletDto;
import by.kovalenko.exception.InsufficientFundsException;
import by.kovalenko.service.GameService;
import by.kovalenko.service.WalletService;
import by.kovalenko.util.GameStatusName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@SessionAttributes("wallet")
public class UserCapabilityController {
    private final GameService gameService;
    private final WalletService walletService;

    @PostMapping(path = "/createGame")
    public String createGame(
            @SessionAttribute(name = "user") UserDto userDto,
            @SessionAttribute(name = "wallet") WalletDto walletDto,
            @RequestParam(name = "creationBet") double creationBet,
            Pageable pageable,
            Model model
    ) {
        try {
            gameService.createGame(userDto.getId(), walletDto, creationBet);
            WalletDto newWalletDto = walletService.findById(userDto.getWallet().getId());
            model.addAttribute("wallet", newWalletDto);
        } catch (InsufficientFundsException e) {
            model.addAttribute("error", e.getMessage());
            return "homePage";
        }
        Page<GameDto> allCreatedGames = gameService.findAllCreatedGames(userDto, pageable);
        model.addAttribute("allCreatedGames", allCreatedGames.getContent());
        model.addAttribute("page", pageable.getPageNumber() + 1);
        model.addAttribute("pageCount", allCreatedGames.getTotalPages());
        return "createGame";
    }

    @GetMapping(path = "/addFunds")
    public String addFunds() {
        return "addFunds";
    }

    @PostMapping(path = "/addFunds")
    public String afterAccountReplenishment(@SessionAttribute(name = "wallet") WalletDto walletDto,
                                            @RequestParam(name = "depositAmount") double depositAmount
    ) {
        walletService.addFunds(walletDto.getId(), depositAmount);
        return "redirect:/homePage";
    }

    @GetMapping(path = "/viewAllCreatedGames")
    public String viewAllCreatedGames(
            @SessionAttribute(name = "user") UserDto userDto,
            Pageable pageable,
            Model model
    ) {
        Page<GameDto> allCreatedGames = gameService.findAllCreatedGames(userDto, pageable);
        model.addAttribute("allCreatedGames", allCreatedGames.getContent());
        model.addAttribute("page", pageable.getPageNumber() + 1);
        model.addAttribute("pageCount", allCreatedGames.getTotalPages());
        return "createGame";
    }

    @GetMapping(path = "/myGames")
    public String myGame(
            @SessionAttribute(name = "user") UserDto userDto,
            @RequestParam(name = "pageFirstTable") int pageFirstTable,
            @RequestParam(name = "sizeFirstTable") int sizeFirstTable,
            @RequestParam(name = "pageSecondTable") int pageSecondTable,
            @RequestParam(name = "sizeSecondTable") int sizeSecondTable,
            Model model
    ) {
        Page<GameDto> allCreatedGames = gameService.findAllCreatedGames(userDto, PageRequest.of(pageFirstTable, sizeFirstTable));
        model.addAttribute("allCreatedGames", allCreatedGames.getContent());
        model.addAttribute("pageFirstTable", pageFirstTable + 1);
        model.addAttribute("pageCountFirstTable", allCreatedGames.getTotalPages());
        Page<GameDto> allAttachedGames = gameService.findAllAttachedGames(userDto, PageRequest.of(pageSecondTable, sizeSecondTable));
        model.addAttribute("allAttachedGames", allAttachedGames.getContent());
        model.addAttribute("pageSecondTable", pageSecondTable + 1);
        model.addAttribute("pageCountSecondTable", allAttachedGames.getTotalPages());
        return "myGames";
    }

    @GetMapping(path = "/listAvailableGames")
    public String listAvailableGames(
            @SessionAttribute(name = "user") UserDto userDto,
            Pageable pageable,
            Model model
    ) {
        Page<GameDto> pageAvailableGames = gameService.findAllByGameStatusAndCreatorIsNot(
                GameStatusName.PLANNED, userDto, pageable);
        model.addAttribute("listAvailableGames", pageAvailableGames.getContent());
        model.addAttribute("page", pageable.getPageNumber() + 1);
        model.addAttribute("pageCount", pageAvailableGames.getTotalPages());
        return "listAvailableGames";
    }

    @GetMapping(path = "/viewAllAttachedGames")
    public String viewAllAttachedGames(
            @SessionAttribute(name = "user") UserDto userDto,
            Pageable pageable,
            Model model
    ) {
        Page<GameDto> allAttachedGames = gameService.findAllAttachedGames(userDto, pageable);
        model.addAttribute("allAttachedGames", allAttachedGames.getContent());
        model.addAttribute("page", pageable.getPageNumber() + 1);
        model.addAttribute("pageCount", allAttachedGames.getTotalPages());
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
            Pageable pageable,
            Model model
    ) {
        try {
            gameService.addUserToGame(userDto, walletDto, id, connectionBet);
            WalletDto newWalletDto = walletService.findById(userDto.getWallet().getId());
            model.addAttribute("wallet", newWalletDto);
        } catch (InsufficientFundsException e) {
            model.addAttribute("error", e.getMessage());
            return "homePage";
        }
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("size", pageable.getPageSize());
        return "redirect:/listAvailableGames";
    }
}
