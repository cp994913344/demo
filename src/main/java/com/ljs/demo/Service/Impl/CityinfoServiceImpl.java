package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.CityinfoService;
import com.ljs.demo.pojo.domain.Cityinfo;
import com.ljs.demo.pojo.mapper.CityinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CityinfoServiceImpl implements CityinfoService {

    @Autowired
    CityinfoMapper cityinfoMapper;

    @Override
    public Cityinfo selectByPrimaryKey(Integer id) {
        return cityinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Cityinfo quretByName(String cityname) {
        return cityinfoMapper.queryByName(cityname);
    }

    @Override
    public List<Cityinfo> queryHotCity() {
        return cityinfoMapper.queryHotCity();
    }

    @Override
    public List<Cityinfo> queryCity() {
        return cityinfoMapper.queryCity();
    }

    @Override
    public String queryProvince(String cityname) {
        return cityinfoMapper.queryProvince(cityname);
    }

    @Override
    public String queryArea(String province) {
        return cityinfoMapper.queryArea(province);
    }

    @Override
    public int deleteCity(Integer id) {
        return cityinfoMapper.deleteCity(id);
    }
}
