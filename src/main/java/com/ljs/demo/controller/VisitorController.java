package com.ljs.demo.controller;

import com.ljs.demo.common.mail.VisitorServcie;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    VisitorServcie visitorServcie;

    @RequestMapping(value = "/selectById")
    public ResponseMessage selectByPrimaryKey(){
        Visitor visitor = visitorServcie.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", visitor);
        return ResponseMessage.ok("",visitor);
    }
}
