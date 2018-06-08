package com.ljs.demo.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljs.demo.Service.ScenicService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.StaticClass;
import com.ljs.demo.pojo.domain.Scenic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 后台管理查询所有景点
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/queryScenic")
    public ResponseMessage queryScenic(@RequestParam("pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, StaticClass.pageSize);
        List<Scenic> scenicList = scenicService.queryScenic();
        log.info("查询景点链表|[{}]|",scenicList);
        PageInfo pageInfo = new PageInfo(scenicList);
        Page page = (Page)scenicList;
        log.info("查询景点接口出参|[{}]|",scenicList);
        return ResponseMessage.pageList("景点分页链表",page,pageInfo);
    }
    /**
     * 删除城市
     * @param scenicid
     * @return
     */
    @GetMapping(value = "/deleteScenic")
    public ResponseMessage deleteCity(@RequestParam("scenicid") Integer scenicid){
        log.info("接口传入数据cityinfoid|[{}]|",scenicid);
        scenicService.deleteScenic(scenicid);
        return ResponseMessage.ok("删除成功!");
    }
}
