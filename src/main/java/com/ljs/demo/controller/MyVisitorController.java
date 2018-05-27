package com.ljs.demo.controller;


import com.ljs.demo.Service.MyVisitorService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.MyVisitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/myvisitor")
public class MyVisitorController {

    @Autowired
    MyVisitorService myVisitorService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        MyVisitor myVisitor = myVisitorService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", myVisitor);
        return ResponseMessage.ok("",myVisitor);
    }
}
