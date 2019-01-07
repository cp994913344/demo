package com.ljs.demo.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljs.demo.Service.*;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.StaticClass;
import com.ljs.demo.pojo.domain.*;
import com.ljs.demo.pojo.vo.TourVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "/cityinfo")
public class CityInfoController {

    @Autowired
    CityinfoService cityinfoService;

    @Autowired
    TourService tourService;

    @Autowired
    ToVisitorService toVisitorService;

    @Autowired
    ScenicService scenicService;

    @Autowired
    StrategyService strategyService;

    @Autowired
    ServiceInfoService serviceInfoService;

    /**
     * 进入首页查询热门数据接口
     *
     * @return
     */
    @RequestMapping(value = "/queryHot")
    public ResponseMessage queryHot() {
        List<Cityinfo> cityinfoList = cityinfoService.queryHotCity();
        log.info("热门城市列表[{}]", cityinfoList);
        List<Tour> tourList = tourService.queryHotTour();
        log.info("精选导游列表[{}]", tourList);
        List<ToVisitor> toVisitorList = toVisitorService.queryHotToVisitor();
        log.info("最新同游列表[{}]", toVisitorList);
        List<Scenic> scenicList = scenicService.queryHotScenic();
        log.info("热门景点列表[{}]", scenicList);
        List<Strategy> strategyList = strategyService.queryHotStrategy();
        log.info("热门攻略列表[{}]", strategyList);

        Map map = new HashMap();
        map.put("cityinfoList", cityinfoList);
        map.put("tourList", tourList);
        map.put("toVisitorList", toVisitorList);
        map.put("scenicList", scenicList);
        map.put("strategyList", strategyList);
        return ResponseMessage.ok("首页热门数据", map);
    }

    /**
     * 根据ID查询城市信息
     *
     * @return
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById() {
        log.info("|对外接口|入参[{}]");
        Cityinfo cityinfo = cityinfoService.selectByPrimaryKey(2);
        log.info("|对外接口|返回参数[{}]", cityinfo);
        return ResponseMessage.ok("", cityinfo);
    }

    /**
     * 根据城市名查询城市信息
     *
     * @param cityname
     * @return
     */
    @RequestMapping(value = "/queryByName")
    public ResponseMessage queryByName(@RequestParam("cityname") String cityname) {
        log.info("|根据城市名查询城市信息对外接口|入参[{}]", cityname);
        //Cityinfo cityinfo = cityinfoService.quretByName("沈");
        Cityinfo cityinfo = cityinfoService.quretByName(cityname);
        log.info("|对外接口|返回城市信息[{}]", cityinfo);

        //根据城市UID查询对应的导游和伴游
        List<TourVo> tourList = tourService.queryByCityUID(cityinfo.getUuid());
        log.info("|对外接口|返回导游信息[{}]", tourList);

        //根据导游/伴游的UID查询对应的服务列表,并存到导游列表中
        for (int i = 0; i < tourList.size(); i++) {
            List<String> serviceInfoList = serviceInfoService.queryByTourUid(tourList.get(i).getUuid());
            tourList.get(i).setServiceList(serviceInfoList);
        }

        //根据城市UID查询对应的景点列表
        List<Scenic> scenicList = scenicService.queryByCityUid(cityinfo.getUuid());
        log.info("|对外接口|返回城市攻略信息[{}]", scenicList);

        //根据城市UID查询对应的城市攻略
        List<Strategy> strategyList = strategyService.queryByCityUid(cityinfo.getUuid());
        log.info("|对外接口|返回城市攻略信息[{}]", strategyList);

        Map cityMap = new HashMap();
        cityMap.put("cityinfo", cityinfo);//城市信息
        cityMap.put("tourlist", tourList);//导游列表
        cityMap.put("scenicList", scenicList);//景点列表
        cityMap.put("strategyList", strategyList);//攻略列表
        return ResponseMessage.ok("城市所有信息", cityMap);
    }

    /**
     * 后台检索城市
     *
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/queryCity")
    public ResponseMessage queryCity(@RequestParam("pageNum") Integer pageNum) {
        log.info("接口传入数据pageNum|[{}]|", pageNum);
        int pageSize = StaticClass.pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Cityinfo> cityinfoList = cityinfoService.queryCity();
        PageInfo pageInfo = new PageInfo(cityinfoList);
        Page<Cityinfo> page = (Page) cityinfoList;
        log.info("查询城市接口出参|[{}]|", cityinfoList);
        return ResponseMessage.pageList("城市分页链表", page, pageInfo);
    }

    /**
     * 删除城市
     *
     * @param cityinfoid
     * @return
     */
    @GetMapping(value = "/deleteCity")
    public ResponseMessage deleteCity(@RequestParam("cityinfoid") Integer cityinfoid) {
        log.info("接口传入数据cityinfoid|[{}]|", cityinfoid);
        cityinfoService.deleteCity(cityinfoid);
        return ResponseMessage.ok("删除成功!");
    }

    /**
     * 管理员插入城市
     *
     * @param cityinfo
     * @return
     */
    @RequestMapping(value = "/insertCity")
    public ResponseMessage insertCity(Cityinfo cityinfo) {
        log.info("接口传入数据cityinfoid|[{}]|", cityinfo);
        cityinfo.setUuid(GetUuid.uuid);
        cityinfo.setStatus(0);
        cityinfoService.insertSelective(cityinfo);
        return ResponseMessage.ok("添加成功");
    }
}
