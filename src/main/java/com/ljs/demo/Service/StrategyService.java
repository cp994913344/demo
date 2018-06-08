package com.ljs.demo.Service;

import com.ljs.demo.pojo.domain.Strategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StrategyService {

    Strategy selectByPrimaryKey(Integer id);

    List<Strategy> queryByCityUid(String cityUid);

    List<Strategy> queryBySceUid(String SceUId);

    List<Strategy> queryHotStrategy();

    List<Strategy> queryStrategy();

    int deleteStrategy(Integer id);
}
