package com.lmc.controller;

import com.lmc.bean.AvGirl;
import com.lmc.service.AvGirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AvController {

    @Autowired
    AvGirlService avGirlService;

    @RequestMapping("/avgirl")
    @ResponseBody
    public List<AvGirl> getGirl(){
        return avGirlService.findAll();
    }

    @RequestMapping("/avgirlId")
    @ResponseBody
    public AvGirl getGirl(int id){
        return avGirlService.findById(id);
    }
}
