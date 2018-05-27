package com.ljs.demo.controller;

import com.ljs.demo.Service.TourService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Tour;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/tour")
public class TourController {

    @Autowired
    TourService tourService;

    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        Tour tour = tourService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", tour);
        return ResponseMessage.ok("",tour);
    }
}
