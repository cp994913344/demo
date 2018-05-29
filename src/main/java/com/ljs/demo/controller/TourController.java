package com.ljs.demo.controller;

import com.ljs.demo.Service.CityinfoService;
import com.ljs.demo.Service.TourService;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Cityinfo;
import com.ljs.demo.pojo.domain.Tour;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/tour")
public class TourController {

    @Autowired
    TourService tourService;

    @Autowired
    CityinfoService cityinfoService;

    /**
     * 通过导游/伴游ID查询详情
     *
     * @return
     */
    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById(){
        Tour tour = tourService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", tour);
        return ResponseMessage.ok("",tour);
    }

    /**
     * 用户申请成为导游
     *
     * @return
     */
    @RequestMapping(value = "/insertTour")
    public ResponseMessage insertTour(Tour tour1, @RequestParam("visitorUuid") String visitorUuid,
                                      @RequestParam("birthday") String birthday){
        log.info("|对外接口|入参[{}]"/*, tour,visitorUuid,birthday*/);
        //根据服务城市名查询城市信息表的uuid
        Cityinfo c = cityinfoService.quretByName("沈阳");
        //Cityinfo c = cityinfoService.quretByName(tour.getCityservice());
        String cityuuid = c.getUuid();
        Tour tour = new Tour();
        tour.setUuid(GetUuid.uuid);
        tour.setVisitorid("2b8a0a979ea34e5c84cccd1b908e1cf3");
        tour.setCityinfoid(cityuuid);
        tour.setStatus("0");
        int i = tourService.insertTour(tour);
        if(i>0){
            return ResponseMessage.ok("申请成功",i);
        }
        return ResponseMessage.error("申请失败");
    }


}
