package com.ljs.demo.controller;

import com.ljs.demo.Repository.GoodJPA;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Good;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/good")
public class GoodController {

    @Autowired
    GoodJPA goodJPA;

    @RequestMapping("findAll")
    public ResponseMessage findAll(){
        List<Good> goods = goodJPA.findAll();
        log.info("接口返回的商品列表|{}|",goods);
        return ResponseMessage.ok(goods);
    }
}
