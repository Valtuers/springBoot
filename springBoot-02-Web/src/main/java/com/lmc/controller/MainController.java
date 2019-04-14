package com.lmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/111")
    public String main(Model model){
        model.addAttribute("name","222");
        return "main/main.html";
    }
}
