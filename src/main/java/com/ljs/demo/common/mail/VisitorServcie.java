package com.ljs.demo.common.mail;

import com.ljs.demo.pojo.domain.Visitor;
import org.springframework.stereotype.Service;

@Service
public interface VisitorServcie {

    Visitor selectByPrimaryKey(Integer visitorid);
}
