package com.sajid.librarymanagementsystem.controller;

import com.sajid.librarymanagementsystem.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("login")
    public String loginPage(Model model){
        model.addAttribute("dto",new LoginDto("", "", false));
        return "login";
    }
    @GetMapping("forgetPassword")
    public String forgetPassword(Model model){
            model.addAttribute("dto",new LoginDto("", "", false));
            return "redirect:/login?forgetPassword";
    }

    @GetMapping("newaccount")
    public String newaccount(Model model){
        model.addAttribute("dto",new LoginDto("", "", false));
        return "redirect:/login?newaccount";
    }

    @PostMapping("login")
    public String login(@ModelAttribute LoginDto dto,  Model model){
        if(dto.email().equals(dto.password())){
            return "redirect:/dashboard";
        }
        else {
            return "redirect:/login?loginFailed";
        }

    }

}
