package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Visitor;
import org.springframework.stereotype.Service;

@Service
public interface VisitorServcie {

    Visitor selectByPrimaryKey(Integer visitorid);
}
