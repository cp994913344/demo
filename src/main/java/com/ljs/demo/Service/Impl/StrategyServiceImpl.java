package com.ljs.demo.Service.Impl;

import com.ljs.demo.Service.StrategyService;
import com.ljs.demo.pojo.domain.Strategy;
import com.ljs.demo.pojo.mapper.StrategyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    StrategyMapper strategyMapper;


    @Override
    public Strategy selectByPrimaryKey(Integer id) {
        return strategyMapper.selectByPrimaryKey(id);
    }
}
