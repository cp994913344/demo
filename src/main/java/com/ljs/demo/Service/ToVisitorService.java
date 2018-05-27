package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.ToVisitor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToVisitorService {

    ToVisitor selectByPrimaryKey(Integer id);

    List<ToVisitor> selectList();

    int insertTovisitor(ToVisitor toVisitor);
}
