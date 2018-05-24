package com.ljs.demo.controller;

import com.ljs.demo.Service.ServiceInfoService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.ServiceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/serviceinfo")
public class ServiceInfoController {

    @Autowired
    ServiceInfoService serviceInfoService;

    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        ServiceInfo serviceInfo = serviceInfoService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", serviceInfo);
        return ResponseMessage.ok("",serviceInfo);
    }
}
