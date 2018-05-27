package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.ToVisitor;
import org.springframework.stereotype.Service;

@Service
public interface ToVisitorService {

    ToVisitor selectByPrimaryKey(Integer id);
}
