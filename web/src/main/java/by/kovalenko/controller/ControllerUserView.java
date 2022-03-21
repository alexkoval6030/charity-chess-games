package by.kovalenko.controller;

import by.kovalenko.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerUserView {

    @PostMapping(path = "/api/login")
    public String showApiLogin(Model model) {
        return "login";
    }

    @GetMapping(path = "/form/login")
    public String showFormLogin(Model model) {
        return "login";
    }

    @GetMapping(path = "/api/page")
    public String showApiPage(Model model) {
        return "index";
    }

    @GetMapping(path = "/form/page")
    public String showFormPage(Model model) {
        return "index";
    }

    @GetMapping(path = "/view/page/{page}")
    public String showPageNumber(Model model, @PathVariable("page") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        return "index";
    }

    @GetMapping(path = "/view/page/")
    public String showPage(Model model) {
        return "index";
    }

    @GetMapping(path = "/form/registration")
    public String showFormRegistration(Model model) {
        return "registration";
    }

    @GetMapping(path = "/api/registration")
    public String showApiRegistration(Model model) {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String userRegistration(UserEntity user) {
        return "registration";

    }


}
