package com.lmc.service;

import com.github.pagehelper.PageInfo;
import com.lmc.bean.AvGirl;

import java.util.List;

public interface AvGirlService {

    List<AvGirl> findAll();

    AvGirl findById(int id);

    PageInfo<AvGirl> findByPage(int pageNum,int pageSize);
}
