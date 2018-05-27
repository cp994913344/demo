package com.ljs.demo.controller;

import com.ljs.demo.Service.StrategyService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/strategy")
public class StrategyController {

    @Autowired
    StrategyService strategyService;

    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        Strategy strategy = strategyService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", strategy);
        return ResponseMessage.ok("",strategy);
    }
}
