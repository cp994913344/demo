package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Cityinfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityinfoService {

    Cityinfo selectByPrimaryKey(Integer id);

    Cityinfo quretByName(String cityname);

    List<Cityinfo> queryHotCity();

    List<Cityinfo> queryCity();

    String queryProvince(String cityname);

    String queryArea(String province);

    int deleteCity(Integer id);

    int insertSelective(Cityinfo cityinfo);
}
