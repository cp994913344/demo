package com.ljs.demo.common.mail.Impl;

import com.ljs.demo.common.mail.VisitorServcie;
import com.ljs.demo.pojo.domain.Visitor;
import com.ljs.demo.pojo.mapper.VisitorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VisitorServiceImpl implements VisitorServcie {

    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public Visitor selectByPrimaryKey(Integer visitorid) {
        return visitorMapper.selectByPrimaryKey(visitorid);
    }
}
