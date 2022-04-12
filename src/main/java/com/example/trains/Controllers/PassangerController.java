package com.example.trains.Controllers;

import com.example.trains.Servicies.PassengerService;
import com.example.trains.Servicies.UserService;
import com.example.trains.domain.Passenger;
import com.example.trains.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PassangerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {

        if(passengerService.getProfile(model, user)){
            return "profile";
        }

        return "profile";
    }

    @PostMapping("profile")
    public String updatePassenger(@AuthenticationPrincipal User user,
                                    @RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String password2,
                                  @RequestParam String name,
                                  @RequestParam String surname,
                                  Model model){
            if(( !password.isEmpty() && !password2.isEmpty() && password.equals(password2)) ||
                    (password.isEmpty() && password2.isEmpty())
                ){
                passengerService.updatePassenger(user, email, password, password2, name, surname);
                passengerService.getProfile(model, user);
                return "redirect:/profile";
            }
            passengerService.getProfile(model, user);
            model.addAttribute("message", "Password is not equals");
            return "profile";
    }


}
