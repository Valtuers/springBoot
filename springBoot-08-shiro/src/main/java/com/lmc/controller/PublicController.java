package com.lmc.controller;

import com.lmc.bean.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pub")
public class PublicController {

    @RequestMapping("/index")
    public JsonData index(){
        List<String> videoList = new ArrayList<>();
        videoList.add("视频1");
        videoList.add("视频2");
        videoList.add("视频3");

        return JsonData.buildSuccess(videoList);
    }

    @RequestMapping("/toLogin")
    public JsonData toLogin(){

        return JsonData.buildSuccess("去登录吧");
    }
}
