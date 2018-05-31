package com.ljs.demo.controller;


import com.ljs.demo.Service.ScenicService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Scenic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/scenic")
public class ScenicController {

    @Autowired
    ScenicService scenicService;

    /**
     * 根据景点ID查询景点
     * @return
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        Scenic scenic = scenicService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", scenic);
        return ResponseMessage.ok("",scenic);
    }

    @RequestMapping(value = "/queryHotScenic")
    public ResponseMessage queryHotScenic(){
        List<Scenic> list = scenicService.queryHotScenic();
        log.info("|对外接口|返回参数[{}]", list);
        return ResponseMessage.list("热门景点列表",list.size(),list);
    }
}
