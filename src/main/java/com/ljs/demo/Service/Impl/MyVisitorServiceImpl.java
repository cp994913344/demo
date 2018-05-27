package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.MyVisitorService;
import com.ljs.demo.pojo.domain.MyVisitor;
import com.ljs.demo.pojo.mapper.MyVisitorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyVisitorServiceImpl implements MyVisitorService {

    @Autowired
    MyVisitorMapper myVisitorMapper;

    @Override
    public MyVisitor selectByPrimaryKey(Integer id) {
        return myVisitorMapper.selectByPrimaryKey(id);
    }
}
