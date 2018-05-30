package com.ljs.demo.controller;

import com.ljs.demo.Service.StrategyService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
