package com.lmc.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmc.bean.AvGirl;
import com.lmc.dao.AvGirlMapper;
import com.lmc.service.AvGirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class AvGirlServiceImpl implements AvGirlService {
    @Autowired
    AvGirlMapper avGirlMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<AvGirl> findAll() {
        return avGirlMapper.selectAll();
    }

    @Override
    public AvGirl findById(int id) {
        return avGirlMapper.selectById(id);
    }

    @Override
    public PageInfo<AvGirl> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<AvGirl> list = new PageInfo<>(avGirlMapper.selectAll());
        return list;
    }
}
