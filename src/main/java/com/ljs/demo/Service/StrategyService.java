package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Strategy;
import org.springframework.stereotype.Service;

@Service
public interface StrategyService {

    Strategy selectByPrimaryKey(Integer id);
}
