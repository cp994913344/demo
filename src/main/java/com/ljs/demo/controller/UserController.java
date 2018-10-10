package com.ljs.demo.controller;

import com.ljs.demo.Service.UserService;
import com.ljs.demo.common.constant.redis.RedisClient;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisClient redisClient;

    @GetMapping(value = "/findAll")
    public ResponseMessage findAll() {
        List<User> list = userService.findAll();
        log.info("|对外接口|返回参数[{}]", list);
        redisClient.setLists("list", list);
        System.out.println(redisClient.getListStartEnd("list", 0, 5));
        return ResponseMessage.ok("用户链表", list);
    }

    @RequestMapping(value = "/toMain")
    public ModelAndView toMain() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        logger.info("123",new Exception("ta"));
        return mav;
    }
}
