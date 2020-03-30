package com.jason.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @GetMapping("/login")
    public String toLoginPage(){
        return "admin/login";
    }

    @PostMapping("/success")
    public String loginSuccess(){
        return "redirect:/admin/index";
    }
}
