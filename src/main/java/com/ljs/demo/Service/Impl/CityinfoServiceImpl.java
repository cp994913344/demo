package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.CityinfoService;
import com.ljs.demo.pojo.domain.Cityinfo;
import com.ljs.demo.pojo.mapper.CityinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CityinfoServiceImpl implements CityinfoService {

    @Autowired
    CityinfoMapper cityinfoMapper;

    @Override
    public Cityinfo selectByPrimaryKey(Integer id) {
        return cityinfoMapper.selectByPrimaryKey(id);
    }
}
