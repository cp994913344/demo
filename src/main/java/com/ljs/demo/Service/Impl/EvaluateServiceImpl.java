package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.EvaluateService;
import com.ljs.demo.pojo.domain.Evaluate;
import com.ljs.demo.pojo.mapper.EvaluateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    EvaluateMapper evaluateMapper;

    @Override
    public int insert(Evaluate evaluate) {
        return evaluateMapper.insert(evaluate);
    }
}
