package com.ljs.demo.controller;


import com.ljs.demo.Service.CityinfoService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Cityinfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "cityinfo")
public class CityInfoController {

    @Autowired
    CityinfoService cityinfoService;

    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        Cityinfo cityinfo = cityinfoService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", cityinfo);
        return ResponseMessage.ok("",cityinfo);
    }
}
