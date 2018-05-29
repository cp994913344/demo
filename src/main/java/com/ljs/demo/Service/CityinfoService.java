package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Cityinfo;
import org.springframework.stereotype.Service;

@Service
public interface CityinfoService {

    Cityinfo selectByPrimaryKey(Integer id);

    Cityinfo quretByName(String cityname);
}
