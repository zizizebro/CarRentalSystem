package mum.edu.cs.cs425.project.carrentalsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){

        return "home/homepage";
    }
}
