package com.lmc.controller;

import com.lmc.bean.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/video")
@RestController
public class VideoController {

    @RequestMapping("/find")
    public JsonData findVideo(){
        return JsonData.buildSuccess("视频查找成功");
    }

    @RequestMapping("/buy")
    public JsonData buyVideo(){
        return JsonData.buildSuccess("视频购买成功");
    }

    @RequestMapping("/update")
    public JsonData updateVideo(){
        return JsonData.buildSuccess("视频更新成功");
    }
}
