package com.ljs.demo.controller;

import com.ljs.demo.Service.UserService;
import com.ljs.demo.common.constant.redis.RedisClient;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.SendVerificationCodeUtil;
import com.ljs.demo.pojo.domain.User;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisClient redisClient;


    @PostMapping(value = "/getEmailCode")
    @ResponseBody
    public ResponseMessage getEmailCode(HttpServletRequest request, String saveCode,String email){
        String code = (String)request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        if(StringUtils.isEmpty(saveCode)){
            ResponseMessage.error("请输入验证码");
        }else if(saveCode.equals(code)){
            String  emailCode =  SendVerificationCodeUtil.sendSixCodeMail(email);
            try{
                redisClient.set(email.trim()+SendVerificationCodeUtil.REDIS_EMAIL_CODE,emailCode);
                log.info((String)redisClient.get(email.trim()+SendVerificationCodeUtil.REDIS_EMAIL_CODE));
            }catch (Exception e){
            }
            ResponseMessage.ok("success");
        }else{
            ResponseMessage.error("请输入正确的验证码");
        }
        return ResponseMessage.error("未知的错误");
    }

    /**
     * 登录
     *
     * @return
     */
    @GetMapping(value = "/")
    public String toLogin(){
        return "login";
    }

    /**
     * 主页
     *
     * @return
     */
    @GetMapping(value = "/main")
    public String toMain(){
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

}
