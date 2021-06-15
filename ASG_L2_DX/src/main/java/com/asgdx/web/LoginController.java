package com.asgdx.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @GetMapping("/employee/home")
    public String employeeHome() {
        return "/employee/employee-home";
    }

    @GetMapping("/manager/home")
    public String managerHome() {
        return "/manager/manager-home";
    }

    @GetMapping({"/","/login"})
    public String login() {
        return "main/login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "main/login";
    }

}
