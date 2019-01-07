package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.MyVisitor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyVisitorService {

    MyVisitor selectByPrimaryKey(Integer id);

    List<MyVisitor> queryByToUid(String toUid);

    int insert(MyVisitor myVisitor);
}
