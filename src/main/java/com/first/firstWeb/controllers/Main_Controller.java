package com.first.firstWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main_Controller {
    @GetMapping("/")
    public String main( Model model) {
        model.addAttribute("title", "Main page");
        return "main";
    }

}