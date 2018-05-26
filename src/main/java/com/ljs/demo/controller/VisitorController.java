package com.ljs.demo.controller;

import com.ljs.demo.Service.VisitorServcie;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.constant.redis.RedisClient;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.pojo.domain.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    VisitorServcie visitorServcie;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisClient redisClient;

    @RequestMapping(value = "/selectById")
    public ResponseMessage selectByPrimaryKey() {
        Visitor visitor = visitorServcie.selectByPrimaryKey(1);
        log.info("|对外接口|返回参数[{}]", visitor);
        return ResponseMessage.ok("", visitor);
    }

    /**
     * 登陆
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public ResponseMessage login(/*@RequestParam("phone") String phone , @RequestParam("password") String password*/) throws Exception {
        Visitor visitor = visitorServcie.login("6874521", "0505");
        log.info("|对外接口|返回参数[{}]", visitor);
        if (visitor == null) {
            return ResponseMessage.error("登录失败");
        }
        if(redisClient.get("ljs") != null){
            redisClient.del("ljs");
        }
        redisClient.set("ljs",visitor);
        log.info("redis数据库存储的对象|参数[{}]", redisClient.get("ljs"));
        return ResponseMessage.ok("登陆成功", visitor);
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/register")
    public ResponseMessage register() {
        Visitor visitor = new Visitor();
        visitor.setUuid(GetUuid.uuid);
        visitor.setPhone("15566261421");
        visitor.setPassword("123456");
        log.info("|对外接口|入参[{}]", visitor);
        int i = visitorServcie.register(visitor);
        if (i > 0) {
            return ResponseMessage.ok("注册成功", i);
        }
        return ResponseMessage.error("注册失败");
    }

    /**
     * 修改个人信息
     *
     * @return
     */
    @RequestMapping(value = "/updateInfo")
    public ResponseMessage updateInfo() {
        Visitor vi = new Visitor();
        vi.setUuid(GetUuid.uuid);
        vi.setPhone(null);
        vi.setEmail("ljs687421@163.com");
        vi.setName("ljs");
        vi.setSex("man");
        vi.setCity("辽宁,沈阳");
        vi.setAge("24");
        log.info("|对外接口|入参[{}]", vi);
        int i = visitorServcie.updateInfo(vi, 1);
        if (i > 0) {
            return ResponseMessage.ok("修改成功", i);
        }
        return ResponseMessage.error("修改失败");
    }

    /**
     * 钱包充值
     * @return
     */
    @RequestMapping(value = "/updateWallet")
    public ResponseMessage updatewallet(@RequestParam("wallet") String wallet) {
        Visitor vi = new Visitor();
        vi.setUuid(GetUuid.uuid);
        vi.setWallet(200.00);
        log.info("|对外接口|入参[{}]", vi);
        int i = visitorServcie.updateInfo(vi, 1);
        if (i > 0) {
            return ResponseMessage.ok("充值成功", i);
        }
        return ResponseMessage.error("充值失败");
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/updatePass")
    public ResponseMessage updatePass(/*@RequestParam("oldPass") String oldPass ,
                                      @RequestParam("newPass") String newPass*/) throws Exception {
        //判断原密码是否相同
        /*Visitor visitor = (Visitor) redisClient.get("ljs");
        if(visitor.getPassword() != oldPass){
            return ResponseMessage.error("密码与原密码不同！");
        }*/
        Visitor vi = new Visitor();
        vi.setPassword("0421");
        int i = visitorServcie.updateInfo(vi,1);
        if (i > 0) {
            return ResponseMessage.ok("修改成功", i);
        }
        return ResponseMessage.error("修改失败");
    }

}
