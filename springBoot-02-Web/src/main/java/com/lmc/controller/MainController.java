package com.lmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Date;

@Controller
public class MainController {

    //不使用注解返回json数据
    @RequestMapping("/getJson1")
    public ModelAndView getJson1(){
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView mj = new MappingJackson2JsonView();
        mv.setView(mj);
        mv.addObject("age",22);
        return mv;
    }

    @RequestMapping("/111")
    public String main(Model model){
        model.addAttribute("name","222");
        return "main/main.html";
    }
}
