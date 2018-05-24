package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Evaluate;
import org.springframework.stereotype.Service;

@Service
public interface EvaluateService {

    int insert(Evaluate evaluate);
}
