package com.ljs.demo.controller;

import com.ljs.demo.Service.CityinfoService;
import com.ljs.demo.Service.ServiceInfoService;
import com.ljs.demo.Service.TourService;
import com.ljs.demo.Service.VisitorServcie;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.constant.redis.RedisClient;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.StaticClass;
import com.ljs.demo.pojo.domain.Cityinfo;
import com.ljs.demo.pojo.domain.ServiceInfo;
import com.ljs.demo.pojo.domain.Tour;
import com.ljs.demo.pojo.domain.Visitor;
import com.ljs.demo.pojo.vo.TourVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/tour")
public class TourController {

    @Autowired
    TourService tourService;

    @Autowired
    CityinfoService cityinfoService;

    @Autowired
    VisitorServcie visitorServcie;

    @Autowired
    ServiceInfoService serviceInfoService;

    @Autowired
    RedisClient redisClient;

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
    public ResponseMessage insertTour(Tour tour1, @RequestParam("birthday") String birthday, HttpServletRequest request) throws Exception {
        log.info("|对外接口|入参[{}]", tour1,birthday);
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(StaticClass.LOGIN_CODE);
        if(email == null){
            return ResponseMessage.error("用户未登录");
        }
        Visitor vi = (Visitor) redisClient.get(email+ StaticClass.LOGIN_CODE);
        Visitor visitor = visitorServcie.selectByUid(vi.getUuid());

        //根据服务城市名查询城市信息表的uuid
        Cityinfo c = cityinfoService.quretByName(tour1.getCityservice());
        String cityuuid = c.getUuid();
        Tour tour = new Tour();
        tour.setUuid(GetUuid.uuid);
        tour.setVisitorid(vi.getUuid());
        tour.setCityinfoid(cityuuid);
        tour.setStatus("0");//0 待审核
        tour.setSex(visitor.getSex());
        int i = tourService.insertTour(tour);
        if(i > 0){
            return ResponseMessage.ok("申请成功,请等待审核",i);
        }
        return ResponseMessage.error("申请失败");
    }

    /**
     * 条件检索导游
     * @return
     */
    @RequestMapping(value = "/querySelective")
    public ResponseMessage querySelective(Tour tour,@RequestParam("service") String service){
        log.info("|条件检索导游对外接口|入参[{}]",tour);
        /*Tour tour = new Tour();
        String service = "跟拍摄像";
        tour.setSex("男");
        tour.setLauguage("英语");*/
        List<TourVo> tourList = tourService.querySelective(tour);
        log.info("|导游个数|[{}]",tourList.size());
        for(int i = 0; i < tourList.size(); i++){
            List<String> serviceInfoList = serviceInfoService.queryByTourUid(tourList.get(i).getUuid());
            log.info("服务列表|[{}]",serviceInfoList);
            if(serviceInfoList.contains(service)){
                tourList.get(i).setServiceList(serviceInfoList);
            }
            else {
                tourList.remove(i);
                i--;
            }
        }
        log.info("接口出参|[{}]",tourList);
        return ResponseMessage.list("条件检索导游列表",tourList.size(),tourList);
    }


}
