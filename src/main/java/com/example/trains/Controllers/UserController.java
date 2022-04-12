package com.example.trains.Controllers;

import com.example.trains.Servicies.UserService;
import com.example.trains.domain.Role;
import com.example.trains.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin/")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("user-list")
    public String userList(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "user/user-list";
    }

    @GetMapping("user-edit/{user}")
    public String userEdit(@PathVariable User user, Model model)
    {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/user-edit";
    }

    @PostMapping("user-edit/{user}")
    public String saveUser(@RequestParam("userId") User user,
                           @RequestParam Map<String, String> form){
        if(form != null)
            userService.saveUserRoles(user, form);
        return "redirect:/admin/user-list";
    }
}
