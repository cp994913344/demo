package com.ljs.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljs.demo.Service.*;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.constant.redis.RedisClient;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.StaticClass;
import com.ljs.demo.pojo.domain.*;

import java.util.Date;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "/tovisitor")
public class ToVisitorController {

    @Autowired
    ToVisitorService toVisitorService;

    @Autowired
    StrategyService strategyService;

    @Autowired
    ScenicService scenicService;

    @Autowired
    RedisClient redisClient;

    @Autowired
    VisitorServcie visitorServcie;

    @Autowired
    MyVisitorService myVisitorService;

    /**
     * 根据ID查询同游详情
     *
     * @return tovisitorid
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(@RequestParam("tovisitorid") Integer tovisitorid){
        log.info("|对外接口|入参[{}]", tovisitorid);
        ToVisitor toVisitor = toVisitorService.selectByPrimaryKey(tovisitorid);
        Visitor visitor = visitorServcie.selectByUid(toVisitor.getVisitorid());
        List<MyVisitor> myVisitorList = myVisitorService.queryByToUid(toVisitor.getUuid());
        Map map = new HashMap();
        map.put("toVisitor",toVisitor);
        map.put("visitor",visitor);
        map.put("myVisitorList",myVisitorList);
        return ResponseMessage.ok("同游集合信息",map);
    }

    /**
     * 首页跳转同游
     * 查询同游列表
     * @return
     * @Param pageNum,pageSize
     */
    @RequestMapping(value = "/selectToVisitorList")
    public ResponseMessage selectToVisitorList(@RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize){
        if(StringUtils.isBlank(pageNum.toString()) || StringUtils.isBlank(pageSize.toString())){
            return ResponseMessage.error("分页信息不能为空");
        }
        /*int pageNum = 1;
        int pageSize = 3;*/
        PageHelper.startPage(pageNum,pageSize);
        List<ToVisitor> toVisitorList = toVisitorService.selectList();
        PageInfo pageInfo = new PageInfo(toVisitorList);
        Page page = (Page)toVisitorList;
        log.info("|查询同游列表接口出参|[{}]",pageInfo);

        List<Strategy> strategyList = strategyService.queryHotStrategy();
        log.info("|查询热门攻略列表接口出参|[{}]",strategyList);

        List<Scenic> scenicList = scenicService.queryHotScenic();
        log.info("|查询热门景点列表接口出参|[{}]",scenicList);

        Map map = new HashMap();
        map.put("分页同游列表",page);
        map.put("热门攻略列表",strategyList);
        map.put("热门景点列表",scenicList);
        return ResponseMessage.ok("同游页面信息列表",map);
    }

    /**
     * 当前用户发布同游
     * @param toVisitor
     * @return
     */
    @RequestMapping(value = "/insertTovisitor")
    public ResponseMessage insertTovisitor(ToVisitor toVisitor, @RequestParam("date1") String date1,
                                           HttpServletRequest request) throws Exception {
        log.info("|发布同游接口入参|[{}]",toVisitor);
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(StaticClass.LOGIN_CODE);
        if(email == null){
            return ResponseMessage.error("用户未登录");
        }
        Visitor vi = (Visitor) redisClient.get(email+ StaticClass.LOGIN_CODE);

        if(date1 == null || toVisitor.getTimenum() == null){
            return ResponseMessage.error("请输入出发日期和旅行天数！");
        }
        toVisitor.setUuid(GetUuid.uuid);
        toVisitor.setStatus("1");
        toVisitor.setPresentpart(1);
        toVisitor.setVisitorid(vi.getUuid());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = dateFormat.parse(date1);
        toVisitor.setDate(date2);
        int i = toVisitorService.insertTovisitor(toVisitor);
        if(i == 1){
            /*ToVisitor visitor = toVisitorService.queryByVisitorId(vi.getUuid());
            log.info("|发布同游接口出参|同游信息[{}]",visitor);*/
            return ResponseMessage.ok("发布成功！！！");
        }
        return ResponseMessage.error("发布失败");
    }

    /**
     * 编辑同游
     * @param toVisitor
     * @param datetime
     * @return
     */
    @RequestMapping(value = "/updateTovisitor")
    public ResponseMessage updateTovisitor(ToVisitor toVisitor,@RequestParam("datetime") String datetime) throws ParseException {
        log.info("|发布同游接口入参|[{}]",toVisitor,datetime);
        if(toVisitor.getTovisitorid() == null){
            return ResponseMessage.error("参数错误");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(datetime);
        toVisitor.setDate(date);
        int i = toVisitorService.updateTovisitor(toVisitor);
        return ResponseMessage.error("修改成功");
    }

    /**
     * 根据visitor表的uuid去查该用户发布的同游
     *
     * @return
     */
    @RequestMapping(value = "/queryByVisitorId")
    public ResponseMessage queryByVisitorId(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(StaticClass.LOGIN_CODE);
        if(email == null){
            return ResponseMessage.error("用户未登录");
        }

        Visitor vi = (Visitor) redisClient.get(email+ StaticClass.LOGIN_CODE);
        ToVisitor toVisitor = toVisitorService.queryByVisitorId(vi.getUuid());

        if(toVisitor == null){
            return ResponseMessage.error("查询失败,该用户还未发布同游");
        }
        return ResponseMessage.ok("该用户发布的同游信息",toVisitor);
    }


    /**
     * 根据id删除用户发布的同游
     * @param id
     * @return
     */
    @RequestMapping(value = "delTovisitorById")
    public ResponseMessage delTovisitorById(@RequestParam("id") Integer id){
        log.info("删除同游信息接口入参|[{}]|",id);
        toVisitorService.delTovisitorById(id);
        return ResponseMessage.ok("删除成功");
    }

    /**
     * 条件查询同游列表
     * @param departure
     * @param destination
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/queryBySelective")
    public ResponseMessage queryBySelective(@RequestParam("departure") String departure,
                                            @RequestParam("destination") String destination,
                                            @RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize) throws ParseException {
        ToVisitor toVisitor = new ToVisitor();
        toVisitor.setDeparture(departure);
        toVisitor.setDestination(destination);
       /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(StringUtils.isEmpty(startTime)){
            toVisitor.setTimenum(null);
        }
        Date date = simpleDateFormat.parse(startTime);
        toVisitor.setDate(date);
        if(StringUtils.isNotEmpty(endTime)){
            Date date1 = simpleDateFormat.parse(endTime);
            String time = String.valueOf(date1.getTime() - date.getTime()/(24*60*60*1000));
            toVisitor.setTimenum(time);
            log.info("|行程共几天|[{}]",time);
        }*/
        PageHelper.startPage(pageNum,pageSize);
        List<ToVisitor> toVisitorList = toVisitorService.queryBySelective(toVisitor);
        PageInfo pageInfo = new PageInfo(toVisitorList);
        Page page = (Page)toVisitorList;
        log.info("|条件查询同游列表出参|[{}]",page);
        return ResponseMessage.pageList("条件查询同游列表",page,pageInfo);
    }

    @RequestMapping("/delTovisitor")
    public ResponseMessage delTovisitor(@RequestParam("toVisitorId") Integer tovisitorid){
        log.info("删除同游接口入参|[{}]|",tovisitorid);
        toVisitorService.delTovisitorById(tovisitorid);
        return ResponseMessage.ok("删除成功！");
    }

}
