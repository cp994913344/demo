package com.ljs.demo.controller;

import com.ljs.demo.Service.ToVisitorService;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.ToVisitor;
import com.ljs.demo.pojo.domain.Visitor;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     * @return tovisitorid
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(@RequestParam("tovisitorid") Integer tovisitorid){
        ToVisitor toVisitor = toVisitorService.selectByPrimaryKey(tovisitorid);
        log.info("|对外接口|返回参数[{}]", toVisitor);
        if(toVisitor == null){
            return ResponseMessage.error("查询失败");
        }
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

    /**
     * 当前用户发布同游
     * @param toVisitor
     * @param visitor
     * @return
     */
    @RequestMapping(value = "/insertTovisitor")
    public ResponseMessage insertTovisitor(ToVisitor toVisitor, Visitor visitor,@RequestParam("datetime") String datetime) throws ParseException {
        log.info("|发布同游接口入参|[{}]",toVisitor,visitor);
        if(datetime == null || toVisitor.getTimenum() == null){
            return ResponseMessage.error("请输入出发日期和旅行天数！");
        }
        toVisitor.setUuid(GetUuid.uuid);
        toVisitor.setStatus("1");
        toVisitor.setPresentpart(1);
        toVisitor.setVisitorid(visitor.getUuid());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(datetime);
        toVisitor.setDate(date);
        int i = toVisitorService.insertTovisitor(toVisitor);
        if(i > 0){
            return ResponseMessage.ok("发布成功",i);
        }
        return ResponseMessage.error("发布失败");
    }




}
