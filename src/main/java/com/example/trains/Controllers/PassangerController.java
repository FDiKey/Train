package com.example.trains.Controllers;

import com.example.trains.Repo.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PassangerController {
    @Autowired
    private PassengerRepo passagerRepo;

    @GetMapping("profile")
    public String showProfile(Model model)
    {
        return "profile";
    }

}
