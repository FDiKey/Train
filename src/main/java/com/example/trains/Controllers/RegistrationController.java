package com.example.trains.Controllers;

import com.example.trains.Servicies.UserService;
import com.example.trains.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model)
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String create(User user, Model model){

        boolean isDone = userService.createUser(user);

        if(isDone)
            return "redirect:/login";
        else {
            model.addAttribute("message", "name allready exists");
            return "registration";
        }
    }
}
