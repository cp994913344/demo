package com.ljs.demo.controller;

import com.ljs.demo.Service.*;
import com.ljs.demo.common.constant.redis.RedisClient;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.SendVerificationCodeUtil;
import com.ljs.demo.common.utils.StaticClass;
import com.ljs.demo.pojo.domain.*;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    CityinfoService cityinfoService;

    @Autowired
    TourService tourService;

    @Autowired
    ToVisitorService toVisitorService;

    @Autowired
    StrategyService strategyService;

    @Autowired
    ScenicService scenicService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisClient redisClient;

    @Autowired
    VisitorServcie visitorServcie;

    @PostMapping(value = "/getEmailCode")
    @ResponseBody
    public ResponseMessage getEmailCode(HttpServletRequest request, String saveCode,String email){
        String code = (String)request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        if(StringUtils.isEmpty(saveCode)){
            return ResponseMessage.error("请输入验证码");
        }else if(saveCode.equals(code)){
            String  emailCode =  SendVerificationCodeUtil.sendSixCodeMail(email);
            try{
                redisClient.set(email.trim()+SendVerificationCodeUtil.REDIS_EMAIL_CODE,emailCode);
            }catch (Exception e){
            }
            return ResponseMessage.ok("success");
        }else{
            return ResponseMessage.error("请输入正确的验证码");
        }
    }


    /**
     * 登录
     *
     * @return
     */
    @GetMapping(value = "/login")
    public String toLogin(){
        return "login";
    }

    /**
     * 登录
     *
     * @return
     */
    @GetMapping(value = "/towallet")
    public String towallet(){
        return "wallet";
    }

    /**
     * 管理员页面
     * @return
     */
    @GetMapping(value = "/adminIndex")
    public String toAdmin(){
        return "admin/adminIndex";
    }

    @GetMapping(value = "/tourIndex")
    public String tourIndex(){
        return "tourManage/tourIndex";
    }


    /**
     * 主页
     *
     * @return
     */
    @GetMapping(value = "/")
    public String toMain() {
        return "main";
    }

    /**
     * 密码修改
     *
     * @return
     */
    @GetMapping(value = "/toUpdatePassword")
    public String toUpdatePassword(){
        return "update_password";
    }

    /**
     * 头像修改
     *
     * @return
     */
    @GetMapping(value = "/toMemberAvatar")
    public String toMemberAvatar(){
        return "member_avatar";
    }

    /**
     * 个人信息
     *
     * @return
     */
    @GetMapping(value = "/toMemberInfo")
    public String toMemberInfo(){
        return "member_info";
    }

    /**
     * 导游详情
     *
     * @return
     */
    @GetMapping(value = "/toTourDetails")
    public String toTourDetails(){
        return "apply/tour_details";
    }

    /**
     * 验证是否登录
     */
    @RequestMapping("/returnLogin")
    @ResponseBody
    public ResponseMessage returnLogin(HttpServletRequest request){
        String user =(String)request.getSession().getAttribute(StaticClass.LOGIN_CODE);
        if(StringUtils.isNotEmpty(user)){
            try {
                Visitor visitor =(Visitor)redisClient.get(user+StaticClass.LOGIN_CODE);
                if(visitor!=null){
                    return ResponseMessage.ok(visitor.getName());
                }
            }catch (Exception e){
                log.info("未登录");
            }
        }
        return ResponseMessage.error("");
    }



    /**
     * 获取当前登录用户
     */
    @RequestMapping("/getLoginMessage")
    @ResponseBody
    public ResponseMessage getLoginMessage(HttpServletRequest request){
        String user =(String)request.getSession().getAttribute(StaticClass.LOGIN_CODE);
        if(StringUtils.isNotEmpty(user)){
            try {
                Visitor visitor =(Visitor)redisClient.get(user+StaticClass.LOGIN_CODE);
                if(visitor!=null){
                    Visitor visitor1 = visitorServcie.selectByPrimaryKey(visitor.getVisitorid());
                    return ResponseMessage.ok(visitor.getName(),visitor1);
                }
            }catch (Exception e){
                log.info("未登录");
            }
        }
        return ResponseMessage.error("");
    }


    /**
     * 申请成为向导
     *
     * @return
     */
    @GetMapping(value = "/toApply")
    public String toApply(){
        return "apply/apply";
    }

    /**
     * 申请成为向导填写信息1页面
     *
     * @return
     */
    @GetMapping(value = "/toApplyMessageOne")
    public String toApplyMessageOne(){
        return "apply/apply_message_one";
    }

    /**
     * 搜索导游
     *
     * @return
     */
    @GetMapping(value = "/toTour")
    public String toTour(){
        return "apply/tour";
    }

    @GetMapping(value = "/towith")
    public String towith(){
        return "wodetongyou";
    }


}
