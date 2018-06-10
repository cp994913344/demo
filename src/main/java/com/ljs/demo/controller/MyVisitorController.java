package com.ljs.demo.controller;


import com.ljs.demo.Service.MyVisitorService;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.constant.redis.RedisClient;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.StaticClass;
import com.ljs.demo.pojo.domain.MyVisitor;
import com.ljs.demo.pojo.domain.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/myvisitor")
public class MyVisitorController {

    @Autowired
    MyVisitorService myVisitorService;

    @Autowired
    RedisClient redisClient;

    /**
     *根据myvisitorid查询具体我的同游
     *
     * @return myvisitorid
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(@RequestParam("myvisitorid") Integer myvisitorid){
        log.info("|根据myvisitorid查询具体我的同游对外接口|入参[{}]", myvisitorid);
        if(myvisitorid == null){
            return ResponseMessage.error("myvisitorid不能为空");
        }
        MyVisitor myVisitor = myVisitorService.selectByPrimaryKey(1);
        if(myVisitor == null){
            return ResponseMessage.ok("查询失败");
        }
        log.info("|对外接口|返回参数[{}]", myVisitor);
        return ResponseMessage.ok("",myVisitor);
    }

    /**
     * 根据同游表uuid查询管理同游表所有同游接口入参
     * @param tovisitorUid
     * @return
     */
    @RequestMapping(value = "queryByToUid")
    public ResponseMessage queryByToUid(@RequestParam("tovisitorUid") String tovisitorUid){
        log.info("根据同游表uuid查询管理同游表所有同游接口入参|[{}]|",tovisitorUid);
        List<MyVisitor> myVisitorList = myVisitorService.queryByToUid(tovisitorUid);
        //List<MyVisitor> myVisitorList = myVisitorService.queryByToUid("2b8a0a979ea34e5c84cccd1b908e1cfd");
        log.info("根据同游表uuid查询管理同游表所有同游接口出参|[{}]|",myVisitorList);
        return ResponseMessage.ok("所有参与的用户",myVisitorList);
    }

    /**
     * 同游报名管理
     * @param myVisitor
     * @return
     */
    @RequestMapping(value = "insertMyvisitor")
    public ResponseMessage insertMyvisitor(MyVisitor myVisitor, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(StaticClass.LOGIN_CODE);
        if(email == null){
            return ResponseMessage.error("用户未登录");
        }
        Visitor vi = (Visitor) redisClient.get(email+ StaticClass.LOGIN_CODE);

        log.info("同游报名管理入参|[{}]|",myVisitor);
        myVisitor.setUuid(GetUuid.uuid);
        myVisitor.setStatus(1);//待审核
        myVisitor.setWithphoto(vi.getSculpture());
        myVisitor.setWithsex(vi.getSex());
        myVisitor.setWithname(vi.getName());

        int i = myVisitorService.insert(myVisitor);
        if(i == 0){
            return ResponseMessage.error("报名失败");
        }
        return ResponseMessage.ok("报名成功,请等待审核！");
    }

}
