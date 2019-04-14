package com.lmc.dao;

import com.lmc.bean.AvGirl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AvGirlMapper {

    List<AvGirl> selectAll();

    AvGirl selectById(int id);
}
