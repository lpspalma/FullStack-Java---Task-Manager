package com.asgdx.web;

import com.asgdx.service.UserService;
import com.asgdx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "main/register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm")User userForm, BindingResult bindingResult){
        userValidator.validate(userForm, bindingResult);
        if(bindingResult.hasErrors()){
            return "main/register";
        }
        userService.save(userForm);
        return "redirect:/login";
    }


}
