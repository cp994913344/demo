package com.ljs.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljs.demo.Service.StrategyService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.StaticClass;
import com.ljs.demo.pojo.domain.Scenic;
import com.ljs.demo.pojo.domain.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/strategy")
public class StrategyController {

    @Autowired
    StrategyService strategyService;

    /**
     * 根据攻略ID查看攻略详情
     * @return
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        Strategy strategy = strategyService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", strategy);
        return ResponseMessage.ok("",strategy);
    }

    /**
     * 根据城市Uid查询攻略
     * @return
     */
    @RequestMapping(value = "/queryByCityUid")
    public ResponseMessage queryByCityUid(){
        List<Strategy> list = strategyService.queryByCityUid("bd0e8b7bf1f7403fbebfe2fdbbae0742");
        return ResponseMessage.list("",list.size(),list);
    }

    /**
     * 根据景点Uid查询攻略
     * @return
     */
    @RequestMapping(value = "/queryBySceUid")
    public ResponseMessage queryBySceUid(){
        List<Strategy> list = strategyService.queryBySceUid("bd0e8b7bf1f7403fbebfe2fdbbae0740");
        return ResponseMessage.list("",list.size(),list);
    }

    /**
     * 根据景点Uid查询攻略
     * @return
     */
    @RequestMapping(value = "/queryHotStrategy")
    public ResponseMessage queryHotStrategy(){
        List<Strategy> list = strategyService.queryHotStrategy();
        log.info("|对外接口|返回参数[{}]", list);
        return ResponseMessage.list("热门攻略列表",list.size(),list);
    }

    /**
     * 后台管理景点攻略查询
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/queryStrategy")
    public ResponseMessage queryStrategy(@RequestParam("pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, StaticClass.pageSize);
        List<Strategy> strategyList = strategyService.queryStrategy();
        log.info("查询景点攻略链表|[{}]|",strategyList);
        PageInfo pageInfo = new PageInfo(strategyList);
        Page page = (Page)strategyList;
        log.info("查询景点攻略接口出参|[{}]|",strategyList);
        return ResponseMessage.pageList("景点攻略分页链表",page,pageInfo);
    }

    /**
     * 删除城市
     * @param strategyid
     * @return
     */
    @GetMapping(value = "/deleteStrategy")
    public ResponseMessage deleteCity(@RequestParam("strategyid") Integer strategyid){
        log.info("接口传入数据cityinfoid|[{}]|",strategyid);
        strategyService.deleteStrategy(strategyid);
        return ResponseMessage.ok("删除成功!");
    }

}
