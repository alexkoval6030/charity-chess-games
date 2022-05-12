package by.kovalenko.controller;

import by.kovalenko.dto.UserDto;
import by.kovalenko.dto.WalletDto;
import by.kovalenko.exception.ValidationException;
import by.kovalenko.service.UserService;
import by.kovalenko.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"user", "wallet"})
public class UserViewController {
    private final UserService userService;
    private final WalletService walletService;

    @GetMapping(path = "/")
    public String showLogin() {
        return "login";
    }

    @GetMapping(path = "/login")
    public String showBeforeLogin() {
        return "login";
    }

    @GetMapping(path = "/homePage")
    public String showPage(Authentication authentication, Model model) {
        UserDto user = userService.findByUsername(authentication.getName());
        WalletDto wallet = walletService.findById(user.getWallet().getId());
        model.addAttribute("user", user);
        model.addAttribute("wallet", wallet);
        return "homePage";
    }

    @GetMapping(path = "/partners")
    public String showPartners() {
        return "partners";
    }

    @GetMapping(path = "/registration")
    public String showBeforeRegistration() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String showAfterRegistration(UserDto userDto, Model model) {
        try {
            userService.createUser(userDto);
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "registration";
        }
        return "login";
    }


}
