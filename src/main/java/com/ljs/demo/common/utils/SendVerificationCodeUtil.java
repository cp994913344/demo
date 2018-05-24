package com.ljs.demo.common.utils;

import com.ljs.demo.common.mail.MailService;
import com.ljs.demo.common.utils.RandomSixStringUtil;
import com.ljs.demo.common.utils.SpringContextUtil;

public class SendVerificationCodeUtil {

    public static String sendSixCodeMail(){
        MailService mailService =(MailService)SpringContextUtil.getBean("mailService");
        String code = RandomSixStringUtil.getRandomSix();
        String content="<html>\n" +
                "<body>\n" +
                "<h3>欢迎您注册随行游账号</h3>\n" +
                "<p>您的验证码是: "+code+" </p>\n"+
                "</body>\n" +
                "</html>";
        mailService.sendMimeMessageMail("994913344@qq.com", "随行游验证码", content);
        return code;
    }
}
