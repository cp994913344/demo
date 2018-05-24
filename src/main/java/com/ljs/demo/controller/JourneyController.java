package com.ljs.demo.controller;


import com.ljs.demo.Service.JourneyService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Journey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/journey")
public class JourneyController {

    @Autowired
    JourneyService journeyService;

    @RequestMapping(value = "/selectById")
    public ResponseMessage selectbyId(){
        Journey journey = journeyService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", journey);
        return ResponseMessage.ok("",journey);
    }
}
