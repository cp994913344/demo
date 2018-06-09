package com.ljs.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljs.demo.Service.VisitorServcie;
import com.ljs.demo.common.constant.GetUuid;
import com.ljs.demo.common.constant.redis.RedisClient;
import com.ljs.demo.common.response.ResponseMessage;
import com.ljs.demo.common.utils.SendVerificationCodeUtil;
import com.ljs.demo.common.utils.StaticClass;
import com.ljs.demo.pojo.domain.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * 获取用户信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getVisitor")
    public ResponseMessage getVisittor(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(StaticClass.LOGIN_CODE);
        if(email == null){
            return ResponseMessage.error("用户未登录");
        }
        Visitor vi = (Visitor) redisClient.get(email+ StaticClass.LOGIN_CODE);
        return ResponseMessage.ok("",vi);
    }



    /**
     * 登陆
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public ResponseMessage login(@RequestParam("email") String email , @RequestParam("password") String password, HttpServletRequest request) throws Exception {
        Visitor visitor = visitorServcie.login(email, password);
        log.info("|对外接口|返回参数[{}]", visitor);
        if (visitor == null) {
            return ResponseMessage.error("登录失败!用户名或密码不正确");
        }
        if(redisClient.get(email+ StaticClass.LOGIN_CODE) != null){
            redisClient.del(email+ StaticClass.LOGIN_CODE);
        }
        request.getSession().setAttribute(StaticClass.LOGIN_CODE,email);
        redisClient.set(email+ StaticClass.LOGIN_CODE,visitor);
        log.info("redis数据库存储的对象|参数[{}]", redisClient.get(email+ StaticClass.LOGIN_CODE));
        return ResponseMessage.ok("登陆成功", visitor);
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public ResponseMessage loginOut(HttpServletRequest request) throws Exception {
        String user =(String)request.getSession().getAttribute(StaticClass.LOGIN_CODE);
        if(StringUtils.isNotEmpty(user)) {
            Visitor visitor = (Visitor) redisClient.get(user + StaticClass.LOGIN_CODE);
            if (visitor != null) {
                if (redisClient.get(user + StaticClass.LOGIN_CODE) != null) {
                    redisClient.del(user + StaticClass.LOGIN_CODE);
                }
            }
            request.getSession().setAttribute(StaticClass.LOGIN_CODE, null);
        }
        log.info("redis数据库存储的对象|参数[{}]", redisClient.get(user+ StaticClass.LOGIN_CODE));
        return ResponseMessage.ok("已退出");
    }


    /**
     * 注册
     *
     * @param emailCode  //邮箱验证码
     * @param password
     * @param email
     * @return
     */
    @RequestMapping(value = "/register")
    public ResponseMessage register(String emailCode,String password,String email) throws  Exception{
        if(StringUtils.isNotEmpty(emailCode)&&StringUtils.isNotEmpty(password)&&StringUtils.isNotEmpty(email)){
            String code  = (String)redisClient.get(email+ SendVerificationCodeUtil.REDIS_EMAIL_CODE);
            if(StringUtils.isNotEmpty(code)&&emailCode.equals(code)) {
                Visitor visitor = new Visitor();
                visitor.setUuid(GetUuid.uuid);
                visitor.setEmail(email);
                visitor.setPassword(password);
                visitor.setName(StaticClass.LOGIN_NAME+email);
                log.info("|对外接口|入参[{}]", visitor);
                int i = visitorServcie.register(visitor);
                if (i > 0) {
                    return ResponseMessage.ok("注册成功", i);
                }
            }
        }
        return ResponseMessage.error("注册失败");
    }

    /**
     * 修改个人信息
     *
     * @return
     */
    @RequestMapping(value = "/updateInfo")
    public ResponseMessage updateInfo(Visitor visitor, HttpServletRequest request) throws Exception {
        log.info("|修改个人信息接口入参|[{}]",visitor);
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(StaticClass.LOGIN_CODE);
        if(email == null){
            return ResponseMessage.error("用户未登录");
        }
        Visitor vi = (Visitor) redisClient.get(email+ StaticClass.LOGIN_CODE);
        if(vi!=null&&vi.getVisitorid()!=null){
            Visitor dataVsitor  = visitorServcie.selectByPrimaryKey(vi.getVisitorid());
            if(dataVsitor!=null){
                dataVsitor.setName(visitor.getName());
                dataVsitor.setEmail(visitor.getEmail());
                dataVsitor.setPhone(visitor.getPhone());
                dataVsitor.setAge(visitor.getAge());
                dataVsitor.setCity(visitor.getCity());
                dataVsitor.setSex(visitor.getSex());
                int i = visitorServcie.updateByPrimaryKeySelective(dataVsitor);
                if (i != 1) {
                    return ResponseMessage.ok("修改失败", i);
                }
                redisClient.del(email + StaticClass.LOGIN_CODE);
                redisClient.set(email+ StaticClass.LOGIN_CODE,dataVsitor);
                log.info("|修改个人信息接口出参|[{}]",dataVsitor);
            }
        }
        return ResponseMessage.ok("修改成功");
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
        int i = visitorServcie.updateInfo(vi);
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
    public ResponseMessage updatePass(@RequestParam("oldPass") String oldPass ,
                                      @RequestParam("newPass") String newPass, HttpServletRequest request) throws Exception {
        //判断登录
        String loginUser = (String)request.getSession().getAttribute(StaticClass.LOGIN_CODE);
        if(StringUtils.isNotEmpty(loginUser)){
            //判断原密码是否相同
            Visitor visitor = (Visitor) redisClient.get(loginUser+StaticClass.LOGIN_CODE);
            if(!visitor.getPassword().equals(oldPass)){
                return ResponseMessage.error("密码与原密码不同！");
            }
            Visitor vi = new Visitor();
            vi.setPassword(newPass);
            vi.setVisitorid(visitor.getVisitorid());
            //visitor.setPassword(newPass);
            redisClient.del(loginUser+ StaticClass.LOGIN_CODE);
            redisClient.set(loginUser+ StaticClass.LOGIN_CODE,visitor);
            int i = visitorServcie.updateByPrimaryKeySelective(vi);
            if (i > 0) {
                return ResponseMessage.ok("修改成功", i);
            }
        }else{
            return ResponseMessage.error("未登录");
        }
        return ResponseMessage.error("修改失败");
    }

    /**
     *
     * @param emailCode
     * @return
     */
    @RequestMapping(value = "/resetPass")
    public ResponseMessage resetPass(@RequestParam("emailCode") String emailCode, @RequestParam("email") String email,
                                     @RequestParam("newPass") String newPass) throws Exception {
        log.info("接口入参|[{}]|",newPass);
        if(StringUtils.isNotEmpty(emailCode)){
            String code  = (String)redisClient.get(email+ SendVerificationCodeUtil.REDIS_EMAIL_CODE);
            log.info("|验证码|[{}]",code);
            if(code.equals(emailCode)){
                int i =visitorServcie.resetPass(newPass,email);
                log.info("修改结果|[{}]|",i);
                return ResponseMessage.ok("重置成功",i);
            }
            return ResponseMessage.error("验证码错误");
        }
        return ResponseMessage.error("验证码不能为空");
    }

    /**
     * 后台管理查询所有用户
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/queryVisitor")
    public ResponseMessage queryScenic(@RequestParam("pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, StaticClass.pageSize);
        List<Visitor> visitorList = visitorServcie.queryVisitor();
        log.info("查询景点链表|[{}]|",visitorList);
        PageInfo pageInfo = new PageInfo(visitorList);
        Page page = (Page)visitorList;
        log.info("查询景点接口出参|[{}]|",visitorList);
        return ResponseMessage.pageList("用户分页链表",page,pageInfo);
    }

    /**
     * 删除用户
     * @param visitorid
     * @return
     */
    @GetMapping(value = "/deleteVisitor")
    public ResponseMessage deleteCity(@RequestParam("visitorid") Integer visitorid){
        log.info("接口传入数据cityinfoid|[{}]|",visitorid);
        visitorServcie.deleteVisitor(visitorid);
        return ResponseMessage.ok("删除成功!");
    }


}
