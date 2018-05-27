package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.MyVisitor;
import org.springframework.stereotype.Service;

@Service
public interface MyVisitorService {

    MyVisitor selectByPrimaryKey(Integer id);
}
