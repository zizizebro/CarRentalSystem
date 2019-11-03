package mum.edu.cs.cs425.project.carrentalsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/accessdenied")
    public String accessDenied(){
        return "accessdenied";
    }
}
