package com.ljs.demo.controller;

import com.ljs.demo.Service.TourOrderService;
import com.ljs.demo.Service.TourService;
import com.ljs.demo.common.constant.redis.RedisClient;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.StaticClass;
import com.ljs.demo.pojo.domain.Tour;
import com.ljs.demo.pojo.domain.Tourorder;
import com.ljs.demo.pojo.domain.Visitor;
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
@RequestMapping(value = "/tourorder")
public class TourOrderController {

    @Autowired
    TourOrderService tourOrderService;

    @Autowired
    RedisClient redisClient;

    @Autowired
    TourService tourService;

    @RequestMapping(value = "/selectById")
    public ResponseMessage selectById() {
        Tourorder tourorder = tourOrderService.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", tourorder);
        return ResponseMessage.ok("", tourorder);
    }

    /**
     * 导游后台查询订单接口
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryOrderByTourUid")
    public ResponseMessage queryOrderByTourUid(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(StaticClass.LOGIN_CODE);
        if (email == null) {
            return ResponseMessage.error("用户未登录");
        }
        Visitor vi = (Visitor) redisClient.get(email + StaticClass.LOGIN_CODE);
        if (vi == null) {
            return ResponseMessage.error("未登陆！！！！！");
        }
        Tour tour = tourService.queryByVisitorUid(vi.getUuid());

        List<Tourorder> tourorderList = tourOrderService.queryOrderByTourUid(tour.getUuid());

        log.info("导游后台查询订单接口返回数据|[{}]|", tourorderList);

        return ResponseMessage.list("订单列表", tourorderList.size(), tourorderList);
    }

    /**
     * 后台管理员审批订单通过
     *
     * @param tourorderid
     * @return
     */
    @RequestMapping(value = "/passOrder")
    public ResponseMessage passOrder(@RequestParam("tourorderid") Integer tourorderid) {
        log.info("审批接口入参|[{}]|", tourorderid);
        tourOrderService.passOrder(tourorderid);
        return ResponseMessage.ok("审批通过已完成");
    }

    @RequestMapping(value = "/failOrder")
    public ResponseMessage failOrder(@RequestParam("tourorderid") Integer tourorderid) {
        log.info("审批接口入参|[{}]|", tourorderid);
        tourOrderService.deleteByPrimaryKey(tourorderid);
        return ResponseMessage.ok("审批不通过");
    }
}
