package com.ljs.demo.controller;

import com.ljs.demo.Service.ToVisitorService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.ToVisitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/tovisitor")
public class ToVisitorController {

    @Autowired
    ToVisitorService toVisitorService;

    /**
     * 根据ID查询同游
     *
     * @return
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        ToVisitor toVisitor = toVisitorService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", toVisitor);
        return ResponseMessage.ok("",toVisitor);
    }

    /**
     * 查询同游列表
     *
     * @return
     */
    @RequestMapping(value = "/selectToVisitorList")
    public ResponseMessage selectToVisitorList(){
        List<ToVisitor> list = toVisitorService.selectList();
        log.info("|查询同游列表接口出参|[{}]",list);
        return ResponseMessage.ok("所有同游列表",list);
    }




}
