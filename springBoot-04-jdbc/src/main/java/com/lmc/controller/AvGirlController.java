package com.lmc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AvGirlController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/avgirl")
    @ResponseBody
    public List<Map<String,Object>> avGirl(){
        return jdbcTemplate.queryForList("select * from avgirl");
    }
}
