package com.ljs.demo.controller;

import com.ljs.demo.Service.TourOrderService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Tourorder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/tourorder")
public class TourOrderController {

    @Autowired
    TourOrderService tourOrderService;

    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        Tourorder tourorder = tourOrderService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", tourorder);
        return ResponseMessage.ok("",tourorder);
    }
}
