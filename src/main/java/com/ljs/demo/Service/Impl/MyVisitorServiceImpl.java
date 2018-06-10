package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.MyVisitorService;
import com.ljs.demo.pojo.domain.MyVisitor;
import com.ljs.demo.pojo.mapper.MyVisitorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MyVisitorServiceImpl implements MyVisitorService {

    @Autowired
    MyVisitorMapper myVisitorMapper;

    @Override
    public MyVisitor selectByPrimaryKey(Integer id) {
        return myVisitorMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MyVisitor> queryByToUid(String toUid) {
        return myVisitorMapper.queryByToUid(toUid);
    }
}
