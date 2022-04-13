package by.kovalenko.controller;

import by.kovalenko.dto.UserDto;
import by.kovalenko.exception.ValidationException;
import by.kovalenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerUserView {
    private final UserService userService;

    @Autowired
    public ControllerUserView(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public String showLogin(Model model) {
        return "login";
    }

    @GetMapping(path = "/login")
    public String showBeforeLogin() {
        return "login";
    }

    @GetMapping(path = "/logout")
    public String showAfterLogout() {
        return "login";
    }

    @GetMapping(path = "/homePage")
    public String showPage() {
        return "homePage";
    }

    @GetMapping(path = "/partners")
    public String showPartners() { return "partners"; }

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
