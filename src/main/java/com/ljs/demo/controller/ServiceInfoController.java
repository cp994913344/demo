package com.ljs.demo.controller;

import com.ljs.demo.Service.ServiceInfoService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.ServiceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/serviceinfo")
public class ServiceInfoController {

    @Autowired
    ServiceInfoService serviceInfoService;

    /**
     * 根据服务ID查询服务
     * @return
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        ServiceInfo serviceInfo = serviceInfoService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", serviceInfo);
        return ResponseMessage.ok("",serviceInfo);
    }

    /**
     * 根据导游Uid查询导游的服务
     * @return
     */
    @RequestMapping(value = "/queryByTourUid")
    public ResponseMessage queryByTourUid(){
        List<String> list = serviceInfoService.queryByTourUid("aa5294254f604f48bfa4f76d21da5850");
        log.info("|对外接口|返回参数[{}]", list);
        return ResponseMessage.list("",list.size(),list);
    }
}
