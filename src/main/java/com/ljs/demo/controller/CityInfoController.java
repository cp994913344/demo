package com.ljs.demo.controller;


import com.ljs.demo.Service.CityinfoService;
import com.ljs.demo.Service.ServiceInfoService;
import com.ljs.demo.Service.StrategyService;
import com.ljs.demo.Service.TourService;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Cityinfo;
import com.ljs.demo.pojo.domain.ServiceInfo;
import com.ljs.demo.pojo.domain.Strategy;
import com.ljs.demo.pojo.domain.Tour;
import com.ljs.demo.pojo.vo.TourVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    StrategyService strategyService;

    @Autowired
    ServiceInfoService serviceInfoService;

    /**
     * 根据ID查询城市信息
     *
     * @return
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        log.info("|对外接口|入参[{}]");
        Cityinfo cityinfo = cityinfoService.selectByPrimaryKey(2);
        log.info("|对外接口|返回参数[{}]", cityinfo);
        return ResponseMessage.ok("",cityinfo);
    }

    /**
     * 根据城市名查询城市信息
     * @param cityname
     * @return
     */
    @RequestMapping(value = "/queryByName")
    public ResponseMessage queryByName(@RequestParam("cityname") String cityname){
        log.info("|根据城市名查询城市信息对外接口|入参[{}]",cityname);
        //Cityinfo cityinfo = cityinfoService.quretByName("沈");
        Cityinfo cityinfo = cityinfoService.quretByName(cityname);
        log.info("|对外接口|返回城市信息[{}]",cityinfo);

        //根据城市UID查询对应的导游和伴游
        List<TourVo> tourList = tourService.queryByCityUID(cityinfo.getUuid());
        log.info("|对外接口|返回导游信息[{}]",tourList);

        //根据导游/伴游的UID查询对应的服务列表,并存到导游列表中
        for(int i = 0; i < tourList.size(); i++){
            List<String> serviceInfoList = serviceInfoService.queryByTourUid(tourList.get(i).getUuid());
            tourList.get(i).setServiceList(serviceInfoList);
        }

        //根据城市UID查询对应的城市攻略
        List<Strategy> strategyList = strategyService.queryByCityUid(cityinfo.getUuid());
        log.info("|对外接口|返回城市攻略信息[{}]",strategyList);

        Map cityMap = new HashMap();
        cityMap.put("cityinfo",cityinfo);//城市信息
        cityMap.put("tourlist",tourList);//导游列表
        cityMap.put("strategyList",strategyList);//攻略列表
        return ResponseMessage.ok("城市所有信息",cityMap);
    }
}
