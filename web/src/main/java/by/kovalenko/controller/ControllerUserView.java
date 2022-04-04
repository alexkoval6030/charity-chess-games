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
    public String showBeforeLogin(Model model) {
        return "login";
    }

    @PostMapping(path = "/login")
    public String showAfterLogin(Model model) {
        return "index";
    }

    @GetMapping(path = "/logout")
    public String showAfterLogout(Model model) {
        return "login";
    }

    @GetMapping(path = "/page")
    public String showPage(Model model) {
        return "index";
    }

    @GetMapping(path = "/registration")
    public String showBeforeRegistration(Model model) {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String showAfterRegistration(UserDto userDtoFromController, Model model) {
        try {
            userService.createUser(userDtoFromController);
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "registration";
        }
        return "login";
    }


}
