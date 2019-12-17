package com.lmc.controller;

import com.lmc.bean.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/check")
    public JsonData check(){
        return JsonData.buildSuccess("查看管理员");
    }

    @RequestMapping("/video")
    public JsonData video(){
        return JsonData.buildSuccess("管理视频");
    }
}
