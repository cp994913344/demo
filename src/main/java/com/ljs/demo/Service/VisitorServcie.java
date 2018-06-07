package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Visitor;
import org.springframework.stereotype.Service;

@Service
public interface VisitorServcie {

    Visitor selectByPrimaryKey(Integer visitorid);

    Visitor login(String email,String password);

    int register(Visitor visitor);

    int updateInfo(Visitor visitor,Integer visitorid);

    Visitor selectByUid(String visitorUid);

    int updateByPrimaryKeySelective(Visitor visitor);

    int resetPass(String newPass,String email);
}
